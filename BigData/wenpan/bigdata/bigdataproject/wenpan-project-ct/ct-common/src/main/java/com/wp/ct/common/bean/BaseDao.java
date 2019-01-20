package com.wp.ct.common.bean;

import com.sun.source.doctree.EndElementTree;
import com.wp.ct.common.api.Column;
import com.wp.ct.common.api.RowKey;
import com.wp.ct.common.api.TableRef;
import com.wp.ct.common.constant.Names;
import com.wp.ct.common.constant.ValueConstant;
import com.wp.ct.common.util.DateUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2019/1/7.
 * 基础的数据访问对象
 */
public abstract class BaseDao {

    private ThreadLocal<Connection> connHolder = new ThreadLocal<>();
    //准备Admin对象，用于操作hbase
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();


    //开始前准备一些东西
    protected void start() throws Exception{
        getConnection();
        getAdmin();
    }

    protected void end() throws Exception{
        Admin admin = getAdmin();
        if(admin != null){
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnection();
        if(conn != null){
            conn.close();
            connHolder.remove();
        }
    }

    /**
     * 创建命名空间，如果命名空间存在，则不需要创建，否则创建
     * @param namespace
     */
    protected void createNameSpaceNX(String namespace) throws Exception{
        Admin admin = getAdmin();

        //获取命名空间描述器
       try {
           admin.getNamespaceDescriptor(namespace);
       }catch (NamespaceNotFoundException e){
           //当没有找到这个命名空间的时候，进行创建
           NamespaceDescriptor namespaceDescriptor =
                   NamespaceDescriptor.create(namespace).build();

           //通过admin对象调用方法，然后传入命名空间描述器来创建命名空间
           admin.createNamespace(namespaceDescriptor);
       }
    }

    /**
     * 创建表，如果表存在则删除表后再进行创建
     * 通过传入分区数进行分区建表
     * @param name
     * @param families
     * @param coprocessorClass :使用协处理器
     */
    protected void createTableXX(String name,String coprocessorClass,Integer regionCount,String... families) throws Exception{

        Admin admin = getAdmin();

        TableName tableName = TableName.valueOf(name);

        //使用admin对象判断表是否存在
        if( admin.tableExists(tableName)){
            //表存在，删除表
            deleteTable(name);
        }
        //创建表(使用分区键进行分区创建表)
        createTable(name,coprocessorClass,regionCount,families);
    }

    protected void createTableXX(String name,String... families) throws Exception{

        //不使用分区键进行创建表，只有一个分区
       createTableXX(name,null,null,families);
    }

    private void createTable(String name,String coprocessorClass,Integer regionCount,String...families) throws Exception{
        Admin admin = getAdmin();

        //通过表表述器来hbase中创建一张表
        TableName tableName = TableName.valueOf(name);
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

        if(families == null || families.length == 0){
            //如果没有传列族，则默认是info列族
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }

        for (String family : families) {
            //创建列族，使用列描述器
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(columnDescriptor);
        }

        //与对应的协处理器进行关联建表，该表可以在添加主叫用户的时候，在hbase中自动的添加一条被叫用户的信息
        if(coprocessorClass != null && !"".equals(coprocessorClass)){
            tableDescriptor.addCoprocessor(coprocessorClass);       //建表的时候与协处理器进行关联
        }


        //增加预分区
        if(regionCount == null || regionCount <= 0){    //不做预分区,整个表只有一个分区
            admin.createTable(tableDescriptor);
        }else {
            //分区键
            byte[][] splitKeys = genSplitkeys(regionCount);     //生成分区键
            admin.createTable(tableDescriptor,splitKeys);       //传入分区键，按照分区键进行分区建表
        }

    }


    /**
     * 获取查询时startrow,stoprow集合
     * 比如要查询2018年3月到8月的数据
     * 该方法用于scan扫描hbase中的表的时候，按照指定的区间进行扫描
     * @return
     */
    protected static List<String[]> getStartStopRowkeys(String tel,String start,String end){
        List<String[]> rowkeyss = new ArrayList<>();
        String startTime = start.substring(0,6);    //只取年月
        String endTime = end.substring(0,6);

        //使用日历对象，方便对开始月份的增加
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(DateUtil.parse(startTime,"yyyyMM"));

        Calendar endCal = Calendar.getInstance();
        startCal.setTime(DateUtil.parse(endTime,"yyyyMM"));

        //使用日历对象，依次取得该范围内的每一个月的startRow和stopRow
        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()){

            String nowTime = DateUtil.format(startCal.getTime(),"yyyyMM");

            int regionNum = genRegionNum(tel,startTime);    //根据电话号码+开始时间生成分区号

            //1_133_201803 ~ 1_133_201803|
            String startRow = regionNum + "_" + tel + "_" + nowTime;
            String stopRow = regionNum + "_" + tel + "_" + nowTime + "|" ;

            String [] rowkeys = {startRow,stopRow};
            rowkeyss.add(rowkeys);

            //月份+1
            startCal.add(Calendar.MONTH ,1);
        }

        return rowkeyss;
    }

    /**
     * 计算分区号(0,1,2)
     * 保证同一个电话号码的同一个月的数据都在一个分区中，方便进行查询等操作
     * @return
     */
    protected static int genRegionNum(String tel,String date){

        //13548112164 取后四位
        String usercode = tel.substring(tel.length() - 4);
        //20180101121200
        String yearMonth = date.substring(0,6);     //仅截取年和月

        int userCodeHash = usercode.hashCode();
        int yearMonthHash = yearMonth.hashCode();

        //crc校验异或算法
        int crc = Math.abs(userCodeHash ^ yearMonthHash);

        //取模
        int regionNum = crc % ValueConstant.REGION_COUNT;

        return regionNum;
    }


    /**
     * 生成分区键
     * @param regionCount
     * @return
     */
    private byte[][] genSplitkeys( int regionCount){

        int splitkeyCount = regionCount - 1;    //分区键的个数

        /**
         *0,1,2,3,4
         * 0000111
         * 1111000
         * 2222999
         * (负无穷,0|—),  [0|,1|)  ,  [1|,正无穷）
         */
        List<byte[]> bsList = new ArrayList<>();
        byte[][] bs = new byte[splitkeyCount][];

        //构造分区键
        for (int i = 0;i < splitkeyCount;i++){
            String splitkey = i + "|";
            bsList.add(Bytes.toBytes(splitkey));
        }
        bsList.toArray(bs);

        return bs;
    }

    /**
     * 增加对象,自动封装数据，将对象的数据直接保存到hbase中去
     * 通过从对象中得到表名，rowkey，列名
     * @param obj
     */
    protected void putData(Object obj) throws Exception{

        //反射：
        Class clazz = obj.getClass();               //使用反射取得传入的类的对象
        TableRef tableRef = (TableRef)clazz.getAnnotation(TableRef.class);  //使用反射来获取该对象上的注解，以获取表名
        String tableName = tableRef.value();        //使用注解从对象类中获取表名

        Field[] fs = clazz.getDeclaredFields();     //反射获取该对象的所有属性
        String stringRowkey = "";

        //获取rowkey
        for (Field f : fs) {
            //检查每一个取得的对象属性的头上是否标注了RowKey这个注解，若标注了则取出rowkey作为HBASE的行键
            RowKey rowKey = f.getAnnotation(RowKey.class);//取得对象中的rowkey
            if(rowKey != null){
                f.setAccessible(true);                  //反射获取私有属性
                stringRowkey = (String) f.get(obj);     //取得该属性的值作为rowkey
                break;
            }
        }

        //先获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));       //通过表名获取表
        Put put = new Put(Bytes.toBytes(stringRowkey));

        //获取要插入hbase中的每一列的值以及列名
        for (Field f : fs) {
            Column column = f.getAnnotation(Column.class);
            if(column != null){
                String family = column.family();        //取得列族
                String colName = column.column();       //取得每一个列名，如果没有设置则使用属性作为列名
                if(colName == null || "".equals(colName)){
                    colName = f.getName();
                }
                f.setAccessible(true);
                String value = (String) f.get(obj);     //取得该属性的值作为要插入hbase的列值

                //构造put对象
                put.addColumn(Bytes.toBytes(family),Bytes.toBytes(colName),
                        Bytes.toBytes(value));
            }
        }

        //增加数据
        table.put(put);

        //关闭表
        table.close();
    }

    /**
     * 增加多条数据
     * @param name
     * @param puts
     * @throws Exception
     */
    protected void putData(String name, List<Put> puts) throws Exception{

        //先获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(name));       //通过表名获取表

        //增加数据
        table.put(puts);

        //关闭表
        table.close();
    }

    /**
     * 增加一条数据数据
     * @param name
     * @param put
     */
    protected void putData(String name, Put put) throws Exception{

        //先获取表对象
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(name));       //通过表名获取表

        //增加数据
        table.put(put);

        //关闭表
        table.close();
    }

    /**
     * 删除表
     * @param name
     * @throws Exception
     */
    protected void deleteTable(String name)throws Exception{

        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    /**
     * 获取admin对象用于操作hbase（建表等）
     * protected:该方法只能该类的子类能够访问
     */
    protected synchronized Admin getAdmin() throws IOException {

        Admin admin = adminHolder.get();
        if(admin == null){
            admin = getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }


    /**
     * 获取hbase数据库连接对象
     * protected:该方法只能该类的子类能够访问
     */
    protected synchronized Connection getConnection() throws IOException {

        Connection conn = connHolder.get();
        System.out.println("取得hbase连接--------------------------");
        if(conn == null){
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }
}

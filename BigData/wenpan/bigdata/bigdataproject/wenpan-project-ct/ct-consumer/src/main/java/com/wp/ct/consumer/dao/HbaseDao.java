package com.wp.ct.consumer.dao;

import com.wp.ct.common.bean.BaseDao;
import com.wp.ct.common.constant.Names;
import com.wp.ct.common.constant.ValueConstant;
import com.wp.ct.consumer.bean.Calllog;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/7.
 * Hbase的数据访问对象
 */
public class HbaseDao extends BaseDao {

    /**
     * 初始化
     */
    public void init() throws Exception{
        start();

        //在hbase中创建命名空间和表
        createNameSpaceNX(Names.NAMESPACE.getValue());
        //通过指定的分区数进行建表
        createTableXX(Names.TABLE.getValue(), "com.wp.ct.consumer.coprocessor.InsertCalleeCoprocessor",
                ValueConstant.REGION_COUNT, Names.CF_CALLEE.getValue(),Names.CF_CALLER.getValue());

        end();
    }

    /**
     * 插入对象
     * @param log
     * @throws Exception
     */
    public void insertData(Calllog log) throws Exception{
        //设置传入对象的rowkey
        log.setRowkey(genRegionNum(log.getCall1(),log.getCalltime()) + "_" + log.getCall1() + "_" + log.getCalltime() + "_" + log.getCall2() + "_" +
                "_" + log.getDuration());

        putData(log);
    }

    /**
     * kafka消费者消费到数据后向hbase插入数据
     * @param value
     */
    public void insertData(String value) throws Exception{

        //将通话日志保存到hbase表中

        //1.获取通话日志数据
        String[] values = value.split("\t");
        String call1 = values[0];
        String call2 = values[1];
        String calltime = values[2];
        String duration = values[3];

        //2.创建数据对象

        //rowkey的设计
        // 1.长度原则：
        //      最大的值64kb，推荐长度：10 ~ 100byte
        //      最好是8的倍数，能短则短，rowkey太长会影响性能
        // 2.唯一性原则： rowkey应该具备唯一性
        // 3.散列原则：
        //      3-1）盐值散列：不能使用时间戳直接作为rowkey
        //          在rowkey前面增加随机数
        //      3-2）字符串的反转，比如时间戳不能直接作为rowkey，但是可以将时间戳反转一下。
        //          一般电话号码使用字符串反转较多：135 4811 2164
        //      3-3）计算分区号：hashMap

        //rowkey设计使用计算分区号：rowkey = regionNum + call1 + calltime + call2 + duration
        String rowkey = genRegionNum(call1,calltime) + "_" + call1 + "_" + calltime + "_" + call2 + "_" +
                  duration + "_1";

        //主叫用户的put
        Put put = new Put(Bytes.toBytes(rowkey));

        byte[] family = Bytes.toBytes(Names.CF_CALLER.getValue());

        put.addColumn(family,Bytes.toBytes("call1"),Bytes.toBytes(call1));
        put.addColumn(family,Bytes.toBytes("call2"),Bytes.toBytes(call2));
        put.addColumn(family,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
        put.addColumn(family,Bytes.toBytes("duration"),Bytes.toBytes(duration));
        put.addColumn(family,Bytes.toBytes("flg"),Bytes.toBytes("1"));

        System.out.println("rowkey:  " + rowkey);

        //在想hbase中插入主叫的数据的同时，也需要向hbase表中插入一条被叫的数据，所以就需要两个rowkey和put对象
        String calleeRowkey = genRegionNum(call1,calltime) + "_" + call2 + "_" + calltime + "_" +
                call1 + "_" + duration + "_0";

        //被叫用户的put
        /*Put calleePut = new Put(Bytes.toBytes(calleeRowkey));
        byte[] calleeFamily = Bytes.toBytes(Names.CF_CALLER.getValue());

        calleePut.addColumn(calleeFamily,Bytes.toBytes("call1"),Bytes.toBytes(call2));
        calleePut.addColumn(calleeFamily,Bytes.toBytes("call2"),Bytes.toBytes(call1));
        calleePut.addColumn(calleeFamily,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
        calleePut.addColumn(calleeFamily,Bytes.toBytes("duration"),Bytes.toBytes(duration));
        calleePut.addColumn(calleeFamily,Bytes.toBytes("flg"),Bytes.toBytes("0"));*/

        // 3.保存数据
        List<Put> puts = new ArrayList<>();
        puts.add(put);
        //puts.add(calleePut);
        putData(Names.TABLE.getValue(),puts);    //往表中插入数据

    }
}

package com.wp.ct.consumer.coprocessor;

import com.wp.ct.common.bean.BaseDao;
import com.wp.ct.common.constant.Names;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/9.
 * 使用协处理器来保存被叫用户的数据
 * 在保存了主叫用户的信息后，由hbase自动的保存被叫用户的信息，这样会提升效率
 *
 * 协处理器的使用
 * 1.创建类
 * 2.让表知道协处理类（和表有关联），在建表的时候进行
 * 3.将项目打成的jar包发布到hbase中（关联的jar包也需要发布），并且需要分发到集群中
 *
 */
public class InsertCalleeCoprocessor extends BaseRegionObserver {

    //方法的命名规则
    //prePut
    //doPut: 模板方法设计模式(加一个do作为方法的前缀)
    //  存在父子类
    //  父类搭建算法的骨架
    //      比如：1.tel取用户编码  2.时间去年月  3.异或运算  4.hash散列
    //  子类重写算法的细节
    //      1.tel取后四位  2.201801  3.^ 4. % &
    //postPut


    /**
     * 保存主叫用户数据之后，由hbase自动保存被叫用户的数据
     * @param e
     * @param put       :为主叫用户的put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> e, Put put,
                        WALEdit edit, Durability durability) throws IOException {

        //从hbase中获取表
        Table table = e.getEnvironment().getTable(TableName.valueOf(Names.TABLE.getValue()));//获取hbase环境和表

        //主叫用户的rowkey。通过主叫用户的put得到
        String rowkey = Bytes.toString(put.getRow());
        //主叫用户的rowkey格式：1_133_201805_144_0102_1
        String[] values = rowkey.split("_");

        CoprocessorDao dao = new CoprocessorDao();


        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];
        String flg = values[5];

        //只有主叫用户保存后才需要出发被叫用户的保存，被叫用户put后，不再执行下面代码
        if("1".equals(flg)){
            //生成被叫用户的rowkey
            String calleeRowkey = dao.getRegionNum(call2,calltime) + "_" + call2 + "_" + calltime +
                    "_" + call1 + "_" + duration + "_0" ;

            //保存数据
            Put calleePut = new Put(Bytes.toBytes(calleeRowkey));
            byte[] calleeFamily = Bytes.toBytes(Names.CF_CALLEE.getValue());

            calleePut.addColumn(calleeFamily,Bytes.toBytes("call1"),Bytes.toBytes(call2));
            calleePut.addColumn(calleeFamily,Bytes.toBytes("call2"),Bytes.toBytes(call1));
            calleePut.addColumn(calleeFamily,Bytes.toBytes("calltime"),Bytes.toBytes(calltime));
            calleePut.addColumn(calleeFamily,Bytes.toBytes("duration"),Bytes.toBytes(duration));
            calleePut.addColumn(calleeFamily,Bytes.toBytes("flg"),Bytes.toBytes("0"));
            table.put(calleePut);

            //关闭表
            table.close();
        }


    }

    /**
     * 使用内部类来调用抽象类中的方法来生成分区号
     */
    private class CoprocessorDao extends BaseDao{
        public  int getRegionNum(String tel,String time){
            return genRegionNum(tel,time);
        }
    }

}

package com.wp.ct.analysis.mapper;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/10.
 * 分析数据的mapper，从hbase中取得数据
 */
public class AnalysisTextMapper extends TableMapper<Text,Text> {

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        //取得从hbase中得到的rowkey
        String rowkey = Bytes.toString(key.get());

        //拆分rowkey:5_13548112164_20180105160322_13070668383_0054_1
		//1_15896547821_20181127141128_15833659410_2049_1 column=caller:flg, timestamp=1547174660378, value=1
 
        String[] values = rowkey.split("_");

        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];

        String year = calltime.substring(0,4);
        String month = calltime.substring(0,6);
        String day = calltime.substring(0,8);

        System.out.println("map端取得的rowkey============》:" + rowkey + "    通话时间============》：" + duration);

        //主叫用户 -年
        context.write(new Text(call1 + "_" + year),new Text(duration));
        //主叫用户 -月
        context.write(new Text(call1 + "_" + month),new Text(duration));
        //主叫用户 -日
        context.write(new Text(call1 + "_" + day),new Text(duration));

        //被叫用户 -年
        context.write(new Text(call2 + "_" + year),new Text(duration));
        //被叫用户 -月
        context.write(new Text(call2 + "_" + month),new Text(duration));
        //被叫用户 -日
        context.write(new Text(call2 + "_" + day),new Text(duration));
    }
}

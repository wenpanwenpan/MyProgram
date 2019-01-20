package com.wp.ct.analysis.tool;

import com.wp.ct.analysis.io.MySQLBeanOutputFormat;
import com.wp.ct.analysis.io.MySQLTextOutputFormat;
import com.wp.ct.analysis.kv.AnalysisKey;
import com.wp.ct.analysis.kv.AnalysisValue;
import com.wp.ct.analysis.mapper.AnalysisBeanMapper;
import com.wp.ct.analysis.mapper.AnalysisTextMapper;
import com.wp.ct.analysis.reducer.AnalysisBeanReducer;
import com.wp.ct.analysis.reducer.AnalysisTextReducer;
import com.wp.ct.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;

/**
 * Created by Administrator on 2019/1/10.
 * 分析数据的工具类
 */
public class AnalysisBeanTool implements Tool{

    @Override
    public int run(String[] args) throws Exception {

        Job job = Job.getInstance();
        job.setJarByClass(AnalysisBeanTool.class);

        //设置hbase扫描对象，扫描到的数据作为map方法的输入值
        Scan scan = new Scan();
        //设置只扫描指定的列族，只扫描主叫用户保证不出现重复数据
        scan.addFamily(Bytes.toBytes(Names.CF_CALLER.getValue()));
        scan.setCaching(500);

        //mapper  设置mapper的相关数据信息
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),     //表名
                scan,
                AnalysisBeanMapper.class,       //mapper类
                AnalysisKey.class,              //设置mapper类的输出key
                Text.class,            //设置mapper类的输出value
                job
        );


        //reducer       设置要使用的reducer类
        job.setReducerClass(AnalysisBeanReducer.class);
        //设置reduce方法输出的数据类型
        job.setOutputKeyClass(AnalysisKey.class);
        job.setOutputValueClass(AnalysisValue.class);


        //outputformat      输出到mysql中，自定义输出类
        job.setOutputFormatClass(MySQLBeanOutputFormat.class);

        boolean flg = job.waitForCompletion(true);
        if(flg){
            return JobStatus.State.SUCCEEDED.getValue();
        }else {
            return JobStatus.State.FAILED.getValue();
        }

    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}

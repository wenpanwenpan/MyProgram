package com.wp.ct.analysis.io;

import com.wp.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/10.
 * mysql的数据格式化输出对象
 * 将reduce方法写出的数据保存到mysql中
 */
public class MySQLTextOutputFormat extends OutputFormat<Text,Text> {

    protected  static class MySQLRecordWriter extends RecordWriter<Text,Text>{

        private Connection connection = null;
        private Jedis jedis = null;

        public MySQLRecordWriter(){
            //获取资源
            connection = JDBCUtil.getConnection();
            jedis = new Jedis("cMaster",6379);      //获取redis的远程连接，获取telid，dateid然后插入ct_call表中

        }

        /**
         * 输出数据,通过jdbc写入到mysql中
         * @param key    :reduce方法写出的key
         * @param value  :reduce方法写出的value值
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {

            String[] values = value.toString().split("_");
            String sumcall = values[0];
            String sumduration = values[1];

            PreparedStatement pstat = null;
           try {
               String insertSQL = "insert into ct_call(telid,dateid,sumcall,sumduration)" +
                       " values(?,?,?,?)";
               pstat = connection.prepareStatement(insertSQL);

               //取得reduce方法传过来的key，key里面有从hbase得到的电话号码和时间
               String k = key.toString();
               String[] ks = k.split("_");

               String tel = ks[0].trim();
               String date = ks[1].trim();

               System.out.println("tel : " + tel + "  date:  " + date);

               //Integer.parseInt("" + userMap.get(tel))  Integer.parseInt("" + dateMap.get(date))
               //通过用户电话号码取得用户表中的id      从redis缓存中取得数据 jedis.hget("ct_user",tel)   jedis.hget("ct_date",date)
               pstat.setInt(1,Integer.parseInt(jedis.hget("ct_user",tel)));
               pstat.setInt(2,Integer.parseInt(jedis.hget("ct_date",date)));
               pstat.setInt(3,Integer.parseInt(sumcall));
               pstat.setInt(4,Integer.parseInt(sumduration));

               //执行向数据库插入操作
               pstat.executeUpdate();

           }catch (SQLException e){
               e.printStackTrace();
           }finally {
               if(pstat != null){
                   try {
                       pstat.close();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
           }

        }



        public void test(){
            String ct_user = jedis.hget("ct_user", "13068951256");
            //int aa = Integer.parseInt(ct_user);
            System.out.println("==========>" + ct_user);
        }
        /**
         * 释放资源
         * @param taskAttemptContext
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(jedis != null){
                jedis.close();
            }
        }
    }


    @Test
    public void test(){
        MySQLRecordWriter tt = new MySQLRecordWriter();
        Connection con = JDBCUtil.getConnection();
        System.out.println("+++++++++++++" + con);
        tt.test();
    }

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }

    //下面这段代码，摘抄自源码
    private FileOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null?null:new Path(name);
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if(committer == null){
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }
}

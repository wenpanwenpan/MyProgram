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
 * 使用map集合来缓存数据，不用redis
 */
public class MySQLMapOutputFormat extends OutputFormat<Text,Text> {

    protected  static class MySQLRecordWriter extends RecordWriter<Text,Text>{

        private Connection connection = null;

        //读取mysql中的数据
        //将两张表缓存到内存中
        Map<String,Integer> userMap = new HashMap<>();
        Map<String,Integer> dateMap = new HashMap<>();

        public MySQLRecordWriter(){
            //获取资源
            connection = JDBCUtil.getConnection();
            PreparedStatement pstat = null;
            ResultSet rs = null;
            try {
                String queryUserSql = "select id,tel from ct_user";
                pstat = connection.prepareStatement(queryUserSql);
                rs = pstat.executeQuery();

                while (rs.next()){
                    Integer id = rs.getInt(1);
                    String tel = rs.getString(2);

                    //将用户的Tel和id从数据库中取出，保存到map中，相当于加载到缓存中，方便后面做id映射
                    userMap.put(tel,id);
                }
                rs.close();

                //查询时间
                String queryDateSql = "select id,year,month,day from ct_date";
                pstat = connection.prepareStatement(queryDateSql);
                rs = pstat.executeQuery();
                while (rs.next()){
                    Integer id = rs.getInt(1);
                    String year = rs.getString(2);
                    String month = rs.getString(3);
                    if(month.length() == 1){
                        month = "0" + month;
                    }
                    String day = rs.getString(4);
                    if(day.length() == 1){
                        day = "0" + day;
                    }

                    //将用户的Tel和id从数据库中取出，保存到map中，相当于加载到缓存中，方便后面做id映射
                    dateMap.put(year + month + day,id);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(rs != null){
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(pstat != null){
                    try {
                        pstat.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

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
            connection = JDBCUtil.getConnection();
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

               //通过用户电话号码取得用户表中的id
               pstat.setInt(1,userMap.get(tel));
               pstat.setInt(2,userMap.get(date));
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
               if(connection != null){
                   try {
                       connection.close();
                   } catch (SQLException e) {
                       e.printStackTrace();
                   }
               }
           }
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
        }
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

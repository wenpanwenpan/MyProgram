package com.wp.ct.consumer.bean;

import com.wp.ct.common.bean.Consumer;
import com.wp.ct.common.constant.Names;
import com.wp.ct.consumer.dao.HbaseDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Administrator on 2019/1/7.
 * 通话日志的消费者
 */
public class CalllogConsumer implements Consumer {

    /**
     * 消费数据
     */
    @Override
    public void consume() {

        try {
            //创建配置对象
            Properties prop = new Properties();
            //使用类加载器加载配置文件（kafka消费者的配置文件）
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("consumer.properties"));
            //获取flume采集的数据
            KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(prop);

            //关注主题 ct
            consumer.subscribe(Arrays.asList(Names.TOPIC.getValue()));

            //hbase数据访问对象
            HbaseDao dao = new HbaseDao();
            //初始化
            dao.init();
            //消费数据
            while(true){
                //System.out.println("执行while循环");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    //打印出消费到的消息
                    System.out.println(consumerRecord.value());

                    //System.out.println("将数据写入到hbase中");
                    //将消费到的数据插入到hbase中
                    dao.insertData(consumerRecord.value());

                    //使用对象的形式向hbase中插入数据，将要插入hbase的数据封装为一个对象
                    //Calllog log = new Calllog(consumerRecord.value());
                    //dao.insertData(log);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    @Override
    public void close() throws IOException {

    }
}

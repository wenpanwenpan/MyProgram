package com.wp.ct.producer;

import com.wp.ct.common.bean.Producer;
import com.wp.ct.producer.bean.LocalFileProducer;
import com.wp.ct.producer.io.LocalFileDataIn;
import com.wp.ct.producer.io.LocalFileDataOut;

/**
 * Created by Administrator on 2019/1/3.
 * 启动对象
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {

        if(args.length < 2){
            System.out.println("系统参数不正确，请按照指定的格式传入参数：java -jar Producer.jar path1 path2");
            System.exit(1);
        }
        //构建生产者对象
        Producer producer = new LocalFileProducer();

        //producer.setIn(new LocalFileDataIn("e:\\bigdata\\data\\contact.log"));
        //producer.setOut(new LocalFileDataOut("e:\\bigdata\\data\\call.log"));

        producer.setIn(new LocalFileDataIn(args[0]));
        producer.setOut(new LocalFileDataOut(args[1]));

        //生产数据
        producer.produce();

        //关闭生产者对象
        producer.close();

    }
}

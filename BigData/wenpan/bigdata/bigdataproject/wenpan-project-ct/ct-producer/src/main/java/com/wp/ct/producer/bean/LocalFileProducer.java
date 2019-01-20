package com.wp.ct.producer.bean;

import com.wp.ct.common.bean.DataIn;
import com.wp.ct.common.bean.DataOut;
import com.wp.ct.common.bean.Producer;
import com.wp.ct.common.util.DateUtil;
import com.wp.ct.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/1/3.
 * 本地数据文件的生产者
 */
public class LocalFileProducer implements Producer {

    private DataIn in;
    private DataOut out;
    private volatile boolean flg = true;


    @Override
    public void setIn(DataIn in) {
        this.in = in ;
    }

    @Override
    public void setOut(DataOut out) {
        this.out = out ;
    }

    /**
     * 生产数据
     */
    @Override
    public void produce() throws InterruptedException {

        //读取通讯录的数据,读取后封装为对象集合
        try {
            List<Contact> contacts = in.read(Contact.class);

            //测试输入
            /*for (Contact contact : contacts) {
                System.out.println(contact);
            }*/
            while (flg){

                //从通讯录中随机查找两个电话号码（主叫，被叫）
                int call1Index = new Random().nextInt(contacts.size());
                int call2Index;
                while (true){
                    call2Index = new Random().nextInt(contacts.size());
                    if(call1Index != call2Index){
                        break;
                    }
                }

                //随机取得两个用户
                Contact call1 = contacts.get(call1Index);
                Contact call2 = contacts.get(call2Index);

                //生成随机的通话时间
                String startDate = "20180101000000";
                String endDate = "20190101000000";

                long startTime = DateUtil.parse(startDate,
                        "yyyyMMddHHmmss").getTime();

                long endTime = DateUtil.parse(endDate,
                        "yyyyMMddHHmmss").getTime();
                //通话时间
                long callTime = startTime + (long)((endTime - startTime) * Math.random());

                //通话时间字符串
                String callTimeString = DateUtil.format(new Date(callTime),
                        "yyyyMMddHHmmss");

                //生成随机的通话时长(秒),格式为四位数
                String duration = NumberUtil.format(new Random().nextInt(3000),4);

                //生成通话记录
                Calllog log = new Calllog(call1.getTel(), call2.getTel(), callTimeString, duration);

                System.out.println(log);
                //将通话记录刷写到数据文件中
                out.write(log);
                Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭生产者
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if(in != null){
            in.close();
        }
        if (out != null){
            out.close();
        }
    }
}

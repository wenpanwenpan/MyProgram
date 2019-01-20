package com.wp.ct.producer.io;

import com.wp.ct.common.bean.DataOut;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2019/1/3.
 * 本地文件的数据输出
 */
public class LocalFileDataOut implements DataOut{

    private PrintWriter writer = null;
    public LocalFileDataOut(){

    }

    public LocalFileDataOut(String path){
        setPath(path);
    }

    @Override
    public void setPath(String path) {
       try {
           //可以设置为以追加的方式进行写入到文件，我这里采用以覆盖的方式写入
           writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void write(Object data) {
        write(data.toString());
    }

    @Override
    public void write(String data) {
        writer.println(data);
        writer.flush();     //将流中的数据直接写到文件，不用在缓存中
    }

    @Override
    public void close() throws IOException {
        if (writer != null){
            writer.close();
        }
    }
}

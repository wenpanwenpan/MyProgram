package com.wp.ct.producer.io;

import com.wp.ct.common.bean.Data;
import com.wp.ct.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 * 本地文件数据输入
 * 该类用于从本地文件中读取数据
 */
public class LocalFileDataIn implements DataIn{

    //需要使用该流中的readline方法直接读取一行数据
    private BufferedReader reader = null;

    public LocalFileDataIn(){

    }
    public LocalFileDataIn(String path){
        setPath(path);
    }
    @Override
    public void setPath(String path) {
        //字节流不能直接转换为字符流，需要使用转换流
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),
                    "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() throws IOException {
        return null;
    }

    /**
     * 从本地文件中读取数据封装，返回集合
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {

        List<T> ts = new ArrayList<>();

        //从数据文件中读取所有的数据
        String line = null;

        try {
            while ((line = reader.readLine()) != null){     //当读取的数据不为空的时候
                //将读取的数据转换为指定类型的对象，封装为集合然后返回.通过反射加载对象
                T t = clazz.newInstance();
                t.setValue(line);
                ts.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ts;
    }

    @Override
    public void close() throws IOException {
        if (reader != null){
            reader.close();
        }
    }
}

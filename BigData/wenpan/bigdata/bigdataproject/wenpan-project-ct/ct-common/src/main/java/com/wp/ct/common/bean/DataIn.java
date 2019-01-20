package com.wp.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/1/3.
 */
public interface DataIn extends Closeable{

    public void setPath(String path) throws IOException;

    public Object read() throws IOException;

    //传入什么类型的对象，就返回什么类型的对象集合。只有data的子类才可以传入进来
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException;
}

package com.wp.ct.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Administrator on 2019/1/3.
 */
public interface DataOut extends Closeable {
    public void setPath(String path) throws IOException;

    public void write(Object data);

    public void write(String data);
}

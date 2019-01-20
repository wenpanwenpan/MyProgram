package com.wp.ct.common.bean;

import java.io.Closeable;

/**
 * Created by Administrator on 2019/1/3.
 */
public interface Producer extends Closeable{

    /**
     * 生产数据
     */
    public void produce() throws InterruptedException;

    //数据来源
    public void setIn(DataIn in);

    //数据输出地方
    public void setOut(DataOut out);
}

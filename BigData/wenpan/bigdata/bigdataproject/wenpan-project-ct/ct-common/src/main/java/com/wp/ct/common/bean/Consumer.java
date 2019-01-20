package com.wp.ct.common.bean;

import java.io.Closeable;

/**
 * Created by Administrator on 2019/1/7.
 * 消费接口
 */
public interface Consumer extends Closeable{

    /**
     * 消费数据
     */
    public void consume();
}

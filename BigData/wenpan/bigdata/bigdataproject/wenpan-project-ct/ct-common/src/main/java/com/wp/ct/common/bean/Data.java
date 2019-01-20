package com.wp.ct.common.bean;

/**
 * Created by Administrator on 2019/1/3.
 * 数据对象
 */
public abstract class Data implements Val {

    public String content;

    @Override
    public void setValue(Object val) {
        this.content = (String) val ;
    }

    @Override
    public Object getValue() {
        return this.content;
    }
}


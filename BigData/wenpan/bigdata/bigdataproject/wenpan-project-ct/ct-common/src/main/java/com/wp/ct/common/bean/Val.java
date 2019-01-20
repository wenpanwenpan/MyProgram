package com.wp.ct.common.bean;

import javax.management.ObjectName;

/**
 * Created by Administrator on 2019/1/3.
 * 值对象接口
 */
public interface Val {

    //获取值
    public void setValue(Object val);

    public Object getValue();
}

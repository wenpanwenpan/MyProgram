package com.wp.ct.common.constant;

import com.wp.ct.common.bean.Val;

import javax.naming.Name;

/**
 * Created by Administrator on 2019/1/3.
 * 名称常量枚举类
 */
public enum Names implements Val {
    NAMESPACE("ct")
    ,TABLE("ct:calllog")
    ,CF_CALLER("caller")
    ,CF_CALLEE("callee")        //被叫列族
    ,CF_INFO("info")
    ,TOPIC("ct");

    private String name;
    private Names(){

    }
    private Names(String name){
        this.name = name;
    }

    @Override
    public void setValue(Object val) {
        this.name = (String) val;
    }

    @Override
    public String getValue() {
        return this.name;
    }
}

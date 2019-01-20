package com.wp.ct.common.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2019/1/8.
 */
@Target({ElementType.FIELD})             //用在属性上
@Retention(RetentionPolicy.RUNTIME)     //在运行时使用
public @interface Column {
    String family() default "info";     //表示该注解中family属性的默认值为info
    String column() default ""; //如果不写就使用属性名称作为列名，写了的话就使用写了的名称作为hbase的列名

}

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
public @interface RowKey {
}

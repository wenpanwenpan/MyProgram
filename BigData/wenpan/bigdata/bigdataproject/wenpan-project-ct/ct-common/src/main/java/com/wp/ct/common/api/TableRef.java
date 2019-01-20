package com.wp.ct.common.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * Created by Administrator on 2019/1/8.
 */
@Target({ElementType.TYPE})             //用在类上
@Retention(RetentionPolicy.RUNTIME)     //在运行时使用
public @interface TableRef {
    String value();
}

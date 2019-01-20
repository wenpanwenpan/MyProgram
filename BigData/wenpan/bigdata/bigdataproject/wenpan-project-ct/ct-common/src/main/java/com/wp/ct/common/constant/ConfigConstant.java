package com.wp.ct.common.constant;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2019/1/7.
 * 可以使用多种方法实现常量。比如枚举，常量类，和加载配置文件等
 * 增强扩展性，可以不用将常量在代码中写死
 */
public class ConfigConstant {
    private static Map<String,String> valueMap = new HashMap<>();

    static {
        //专门用于读取配置文件ct.properties,将配置文件加载进来
        ResourceBundle ct = ResourceBundle.getBundle("ct");
        Enumeration<String> enums = ct.getKeys();

        //将配置文件中的配置读取出来，然后以键值对的形式保存到静态集合中
        while(enums.hasMoreElements()){
            String key = enums.nextElement();
            String value = ct.getString(key);
            valueMap.put(key,value);
        }
    }

    public static String getVal(String key){
        return valueMap.get(key);
    }

    public static void main(String[] args) {
        System.out.println(ConfigConstant.getVal("ct.cf.caller"));;
    }
}

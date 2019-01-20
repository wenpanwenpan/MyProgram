package com.wp.ct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/1/5.
 * 日期工具类
 */
public class DateUtil {

    /**
     * 将日期字符串按照指定的模式解析为日期对象
     * @param dataString
     * @param format
     * @return
     */
    public static Date parse(String dataString,String format){

        //传入格式化模式
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date date = null;
        try {
            date = sdf.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将指定的日期按照指定的格式转换为字符串
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}

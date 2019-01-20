package com.wp.ct.web.dao;

import com.wp.ct.web.bean.Calllog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 * 通话日志的数据访问对象,由mybatis进行扫描
 */
public interface CalllogDao {
    List<Calllog> queryMonthDatas(Map<String, Object> paramMap);
}

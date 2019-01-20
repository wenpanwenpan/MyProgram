package com.wp.ct.web.service;

import com.wp.ct.web.bean.Calllog;

import java.util.List;

/**
 * Created by Administrator on 2019/1/17.
 *
 */
public interface CalllogService {
    List<Calllog> queryMonthDatas(String tel, String calltime);
}

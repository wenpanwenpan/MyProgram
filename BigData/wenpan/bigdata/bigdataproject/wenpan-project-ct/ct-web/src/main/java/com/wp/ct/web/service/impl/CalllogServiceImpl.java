package com.wp.ct.web.service.impl;

import com.wp.ct.web.bean.Calllog;
import com.wp.ct.web.dao.CalllogDao;
import com.wp.ct.web.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 * 通话日志的服务对象，由spring进行扫描
 */
@Service
public class CalllogServiceImpl implements CalllogService{

    @Autowired
    private CalllogDao calllogDao;

    /**
     * 查询用户指定时间的通话统计信息
     * @param tel
     * @param calltime
     * @return
     */
    @Override
    public List<Calllog> queryMonthDatas(String tel, String calltime) {

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("tel",tel);
        if (calltime.length() > 4){
            calltime = calltime.substring(0,4);
        }
        //自己将要传递的参数封装为一个map集合
        paramMap.put("year",calltime);

        //该方法传入参数后调用mapper映射文件中的sql查询语句
        return calllogDao.queryMonthDatas(paramMap);
    }
}

package com.wp.ct.web.controller;

import com.wp.ct.web.bean.Calllog;
import com.wp.ct.web.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/17.
 * 通话日志的控制器对象
 */
@Controller
public class CalllogController {

    @Autowired
    private CalllogService calllogService;

    @RequestMapping("/query")
    public String query(){

        return "query";
    }

    //在网页中不能传递对象，所以将  对象===>json==>String
    //@ResponseBody
    @RequestMapping("/view")
    public String view(String tel, String calltime, Model model){

        //查询统计结果
        List<Calllog> logs = calllogService.queryMonthDatas(tel,calltime);
        model.addAttribute("calllogs",logs);

        return "view1";
    }
}

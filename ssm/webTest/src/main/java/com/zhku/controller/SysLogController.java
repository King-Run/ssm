package com.zhku.controller;

import com.zhku.pojo.SysLog;
import com.zhku.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        System.out.println("控制层：查询所有的日志");
        ModelAndView mv =new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");
        return mv;
    }
}

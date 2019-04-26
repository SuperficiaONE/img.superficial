package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/plan")
public class PlanController {

    @RequestMapping("/add.htm")
    public String add(){
        log.info("即将进入添加计划的页面");
        return "/page/plan/add";
    }
    @RequestMapping("/list.htm")
    public String getList(){
        log.info("即将进入获取计划列表页面了");
        return "/page/plan/list";
    }
    @RequestMapping("/show.htm")
    public String showPlan(){
        log.info("进入个人计划展示页面");
        return "/page/plan/show";
    }
}

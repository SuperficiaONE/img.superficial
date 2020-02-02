package com.superficial.img.page;

import com.superficial.img.common.anno.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/plan")
public class PlanController {

    @Api("计划添加页面")
    @RequestMapping("/add.htm")
    public String add(){
        return "/page/plan/add";
    }

    @Api("计划列表页面")
    @RequestMapping("/list.htm")
    public String getList(){
        return "/page/plan/list";
    }

    @Api("计划展示页面")
    @RequestMapping("/show.htm")
    public String showPlan(){
        return "/page/plan/show";
    }


}

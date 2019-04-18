package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/artTemplate")
public class ArtTemplateController {
    @RequestMapping("/add.htm")
    public  String add(){
        log.info("正在进入添加模板页面");
        return "/page/arttemplate/add";
    }
    @RequestMapping("/list.htm")
    public  String list(){
        log.info("正在进入模板列表页面");
        return "/page/arttemplate/list";
    }
}

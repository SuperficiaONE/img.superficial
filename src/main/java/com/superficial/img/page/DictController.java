package com.superficial.img.page;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/dict/")
public class DictController {
    @RequestMapping("add.htm")
    public String add(){
        log.info("即将进入添加字典页面");
        return "/page/dict/add";
    }
    @RequestMapping("list.htm")
    public String list(){
        log.info("即将进入字典列表页面");
        return "/page/dict/list";
    }
}

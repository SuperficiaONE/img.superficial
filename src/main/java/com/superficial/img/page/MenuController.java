package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/menu")
public class MenuController {
    @RequestMapping("/add.htm")
    public String addMenu(){
        log.info("即将进入菜单添加页面");
        return "/page/menu/add";
    }
    @RequestMapping("/list.htm")
    public String getIndex(){
        return "/page/menu/list";
    }
}

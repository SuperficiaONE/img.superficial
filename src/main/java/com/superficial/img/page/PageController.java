package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping("/common.htm")
    public String getCommon() {
        log.info("进入首页");
        return "/common/index";
    }

    @RequestMapping("/menu.htm")
    public String getIndex(){
        return "index";
    }
    @RequestMapping("/")
    public String getDefault(){
        return "/common/index";
    }
    @RequestMapping("/tb/add.htm")
    public String getTbAdd(){
        return "/page/tb/add";
    }

}

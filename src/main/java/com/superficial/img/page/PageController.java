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
        return "/page/menu/list";
    }

    @RequestMapping("/")
    public String getDefault(){
        return "/common/index";
    }

    @RequestMapping("/tb/add.htm")
    public String getTbAdd(){
        return "/page/tb/add";
    }

    @RequestMapping("/test/list.htm")
    public String toList(){
        return "/page/test/list";
    }

    @RequestMapping("/test/test.htm")
    public String toTest(){
        return "/page/test/test";
    }

    @RequestMapping("/module/back.htm" +
            "")
    public String module(){
        return "/page/module/back";
    }


    @RequestMapping("/module/index.htm" +
            "")
    public String moduleConfig(){
        return "/page/module/index";
    }

    @RequestMapping("/third.htm")
    public String third(){
        return "/page/test/third/common-home";
    }

    @RequestMapping("/test/markdown.htm")
    public String toMarkdown(){
        return "/page/test/markdown";
    }

    @RequestMapping("/test/mdTemp.htm")
    public String toMarkdownTemp(){
        return "/page/test/mdTemp";
    }
}

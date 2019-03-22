package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class PageController {


    @RequestMapping("/common.htm")
    public String getIndex() {
        log.info("进入首页");
        return "/common/common";
    }
}

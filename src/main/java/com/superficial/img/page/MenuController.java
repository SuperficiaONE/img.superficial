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
        return "/page/menu/add";
    }
}

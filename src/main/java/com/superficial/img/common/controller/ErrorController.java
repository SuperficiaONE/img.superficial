package com.superficial.img.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ErrorController {
    @RequestMapping("/error/{code}")
    public ModelAndView getIndex(@PathVariable(name = "code")String code) {
        log.info("错误页面404");
        ModelAndView modelAndView = new ModelAndView("/page/error/error");
        modelAndView.addObject("code",code);
        return modelAndView;
    }
}

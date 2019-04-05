package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/page/answer/")
public class AnswerController {


    @RequestMapping("add.htm")
    public  String addAnswer(){
        log.info("进入添加答案页面");
        return "/page/answer/add";
    }
}

package com.superficial.img.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class AnswerController {


    @RequestMapping("/page/answer/add.htm")
    public  String addAnswer(){
        log.info("进入添加答案页面");
        return "/page/answer/add";
    }
}

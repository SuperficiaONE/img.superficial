package com.superficial.img.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    @RequestMapping("/index.html")
    public String getIndex(){
        return "index";
    }
}

package com.superficial.img.page;

import com.superficial.img.common.anno.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class HomeController {

    @Api("首页")
    @RequestMapping(path = {"/","index.htm"})
    public String getDefault(){
        return "/common/index";
    }

    @Api("首页-后台测试")
    @RequestMapping(path = {"back.htm"})
    public String getBack(){
        return "/common/back";
    }
}

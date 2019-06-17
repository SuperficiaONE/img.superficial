package com.superficial.img.page;

import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/page/artTemplate")
public class ArtTemplateController {

    @Autowired
    private ITbArtTemplateService templateService;
    @RequestMapping("/add.htm")
    public  String add(){
        log.info("正在进入添加模板页面");
        return "/page/arttemplate/add";
    }
    @RequestMapping("/list.htm")
    public  String list(){
        log.info("正在进入模板列表页面");
        return "/page/arttemplate/list";
    }
    @RequestMapping("/edite.htm")
    public  String edite(String templateId,Model model){
        TbArtTemplate tbArtTemplate = templateService.selectById(templateId);
        model.addAttribute("vo",tbArtTemplate);
        return "/page/arttemplate/edite";
    }
}

package com.superficial.img.page;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/page/artTemplate")
public class ArtTemplateController {

    @Autowired
    private ITbArtTemplateService templateService;

    @Autowired
    private ITbDictService dictService;

    @RequestMapping("/add.htm")
    public  String add(){
        return "/page/arttemplate/add";
    }

    @RequestMapping("/list.htm")
    public  String list(){

        return "/page/arttemplate/list";
    }

    @RequestMapping("/edite.htm")
    public  String edite(String templateId,Model model){
        return "/page/arttemplate/edite";
    }

    @RequestMapping("/show.htm")
    public  String show(String types,Model model){
        return "/page/arttemplate/show";
    }
}

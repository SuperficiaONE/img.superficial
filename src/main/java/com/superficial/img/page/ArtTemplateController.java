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
        List<TbDict> dictList = dictService.selectList(new EntityWrapper<TbDict>()
                .eq("dict_key", tbArtTemplate.getTemplateType())
                .eq("dict_type", "template_type"));
        model.addAttribute("vo",tbArtTemplate);
        model.addAttribute("dict",dictList!=null && dictList.size()>0?dictList.get(0):null);
        return "/page/arttemplate/edite";
    }
}

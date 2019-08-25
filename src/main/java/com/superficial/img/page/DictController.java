package com.superficial.img.page;


import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.api.dict.vo.TbDictVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/page/dict/")
public class DictController {
    @Autowired
    private ITbDictService dictService;
    @RequestMapping("add.htm")
    public String add(){
        log.info("即将进入添加字典页面");
        return "/page/dict/add";
    }
    @RequestMapping("list.htm")
    public String list(){
        log.info("即将进入字典列表页面");
        return "/page/dict/list";
    }
    @RequestMapping("edit.htm")
    public String edit(String dictId, Model model){
        TbDictVo dictVo = dictService.getDictVo(dictId);
         model.addAttribute("d",dictVo );
        return "/page/dict/edit";
    }
}

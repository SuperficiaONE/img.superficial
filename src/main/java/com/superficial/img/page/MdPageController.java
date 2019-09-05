package com.superficial.img.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MdPageController {

    @RequestMapping("/page/md/list.htm")
    public String list(){
        return "/page/md/list";
    }
    @RequestMapping("/page/md/addOrUpdate.htm")
    public String add(){
        return "/page/md/addOrUpdate";
    }
    @RequestMapping("/page/md/detail.htm")
    public String detail(){
        return "/page/md/detail";
    }

    @RequestMapping("/page/mdcategory/list.htm")
    public String mdCategoryList(){
        return "/page/mdcategory/list";
    }
    @RequestMapping("/page/mdcategory/addOrUpdate.htm")
    public String addMdCategory(){
        return "/page/mdcategory/addOrUpdate";
    }
 }

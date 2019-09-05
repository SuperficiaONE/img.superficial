package com.superficial.img.api.md.service;

import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.md.pojo.TbMdCategory;
import com.superficial.img.api.md.vo.MdCategorySearchVo;
import com.superficial.img.common.vo.LayUIPage;

import java.util.Map;

public interface TbMdCategoryService  extends IService<TbMdCategory> {
    Map<String ,Object> selectLayUIpage(MdCategorySearchVo categorySearchVo);
}

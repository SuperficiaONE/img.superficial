package com.superficial.img.api.md.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.md.mapper.TbMdCategoryMapper;
import com.superficial.img.api.md.pojo.TbMdCategory;
import com.superficial.img.api.md.service.TbMdCategoryService;
import com.superficial.img.api.md.vo.MdCategorySearchVo;
import com.superficial.img.common.vo.LayUIPage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class TbMdCategoryServiceImpl  extends ServiceImpl<TbMdCategoryMapper, TbMdCategory> implements TbMdCategoryService {
    @Override
    public Map<String ,Object> selectLayUIpage(MdCategorySearchVo categorySearchVo) {
        Map<String ,Object> resultMap = new HashMap<>(6);
        Map<String,Object> map = new HashMap<>(6);
        /**
         * searchText
         * page
         * limit
         * create
         */
        if(categorySearchVo==null){
            categorySearchVo = new MdCategorySearchVo();

        }else {
            if(categorySearchVo.getPage()==null){
                categorySearchVo.setPage(1);
            }
            if(categorySearchVo.getLimit()==null){
                categorySearchVo.setLimit(10);
            }

        }
        map.put("index",(categorySearchVo.getPage()-1)*categorySearchVo.getLimit());
        map.put("pageSize",categorySearchVo.getLimit());

        map.put("searchText",categorySearchVo.getSearchText());
        List<TbMdCategory> categoryList = this.baseMapper.getCategoryList(map);
        Integer count = this.baseMapper.getCategoryCount(map);
        resultMap.put("code",0);
        resultMap.put("msg","获取成功");
        resultMap.put("data",categoryList);
        resultMap.put("count",count);
        return resultMap;
    }
}

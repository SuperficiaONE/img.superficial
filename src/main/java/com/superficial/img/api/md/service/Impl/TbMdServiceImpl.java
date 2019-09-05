package com.superficial.img.api.md.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.md.mapper.TbMdMapper;
import com.superficial.img.api.md.pojo.TbMd;
import com.superficial.img.api.md.pojo.TbMdCategory;
import com.superficial.img.api.md.service.TbMdService;
import com.superficial.img.api.md.vo.MdCategorySearchVo;
import com.superficial.img.api.md.vo.MdSearchVo;
import com.superficial.img.api.md.vo.TbMdVo;
import com.superficial.img.common.tool.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbMdServiceImpl extends ServiceImpl<TbMdMapper, TbMd> implements TbMdService {
    @Override
    public TbMdVo selectMdVoById(Long mdId) {
        if(CommonUtil.isEmpty(mdId)){
            return null;
        }
        return this.baseMapper.selectMdVoById(mdId);

    }

    @Override
    public Map<String, Object> selectMdVoList(MdSearchVo mdSearchVo) {
        Map<String ,Object> resultMap = new HashMap<>(6);
        Map<String,Object> map = new HashMap<>(6);

        if(mdSearchVo==null){
            mdSearchVo = new MdSearchVo();

        }else {
            if(mdSearchVo.getPage()==null){
                mdSearchVo.setPage(1);
            }
            if(mdSearchVo.getLimit()==null){
                mdSearchVo.setLimit(10);
            }

        }
        map.put("index",(mdSearchVo.getPage()-1)*mdSearchVo.getLimit());
        map.put("pageSize",mdSearchVo.getLimit());

        map.put("searchText",mdSearchVo.getSearchText());
        List<TbMdVo> categoryList = this.baseMapper.getMdVoList(map);
        Integer count = this.baseMapper.getMdVoCount(map);
        resultMap.put("code",0);
        resultMap.put("msg","获取成功");
        resultMap.put("data",categoryList);
        resultMap.put("count",count);
        return resultMap;
    }
}

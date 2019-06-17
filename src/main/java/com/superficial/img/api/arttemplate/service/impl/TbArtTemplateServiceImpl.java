package com.superficial.img.api.arttemplate.service.impl;

import com.superficial.img.api.arttemplate.mapper.TbArtTemplateMapper;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.common.tool.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 定义艺术模板 服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-04-18
 */
@Service
public class TbArtTemplateServiceImpl extends ServiceImpl<TbArtTemplateMapper, TbArtTemplate> implements ITbArtTemplateService {

    @Override
    public List<String> getTemplateScriptList(String types) {
        if(CommonUtil.isEmpty(types)){
            return null;
        }
        List<String> typeList = Arrays.asList(types.split(","));
        return this.baseMapper.getTemplateScriptList(typeList);
    }

    @Override
    public List<ArtTemplateVo> getArtTemplateVoList(Integer page, Integer pageSize) {
        Integer from = (page-1)*pageSize;
        return this.baseMapper.getArtTemplateVoList(from,pageSize);
    }
}

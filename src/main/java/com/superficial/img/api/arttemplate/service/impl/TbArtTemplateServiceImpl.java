package com.superficial.img.api.arttemplate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.arttemplate.mapper.TbArtTemplateMapper;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
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

    @Override
    public TbArtTemplate insertArtTemplate(TbArtTemplate tbArtTemplate) throws Exception {
        if(CommonUtil.isEmpty(tbArtTemplate)){
           throw  new Exception("参数不能为空");
        }
        if(CommonUtil.isEmpty(tbArtTemplate.getTemplateScript())){
            throw  new Exception("模板脚本参数不能为空");
        }
        if(CommonUtil.isEmpty(tbArtTemplate.getTemplateData())){
            throw  new Exception("模板结构数据参数不能为空");
        }
        if(CommonUtil.isEmpty(tbArtTemplate.getTemplateType())){
            throw  new Exception("模板类型参数不能为空");
        }
        if(selectCount(new EntityWrapper<TbArtTemplate>().eq("template_type",tbArtTemplate.getTemplateType()))>0){
            update(tbArtTemplate,new EntityWrapper<TbArtTemplate>().eq("template_type",tbArtTemplate.getTemplateType()));
        }else {
            String loginName = JwtHelper.getLoginName();
            insert(tbArtTemplate
                    .setCreateAt(new Date())
                    .setId(IdWorker.getId())
                    .setUpdateAt(new Date())
                    .setCreateUser(loginName)
                    .setUpdateUser(loginName)
            );
        }
        return tbArtTemplate;
    }
}

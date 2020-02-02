package com.superficial.img.api.arttemplate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.arttemplate.mapper.TbArtTemplateMapper;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.arttemplate.vo.TemplateDictVo;
import com.superficial.img.api.arttemplate.vo.TemplateSearchVo;
import com.superficial.img.api.dict.mapper.TbDictMapper;
import com.superficial.img.common.Cons;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private TbDictMapper dictMapper;
    @Override
    public List<String> getTemplateScriptList(String types) {
        if(CommonUtil.isEmpty(types)){
            return null;
        }
        List<String> typeList = Arrays.asList(types.split(","));
        return this.baseMapper.getTemplateScriptList(typeList);
    }

    @Override
    public List<ArtTemplateVo> getArtTemplateVoList(Integer page, Integer pageSize,String useType) {
        Integer from = (page-1)*pageSize;
        return this.baseMapper.getArtTemplateVoList(from,pageSize,useType);
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
        dictMapper.updateCanDelete(Cons.DICT_TYPE_TEMPLATE_TYPE,tbArtTemplate.getTemplateType(),0);
        return tbArtTemplate;
    }

    @Override
    public Map<String, Object> getDictList(TemplateSearchVo templateSearchVo) {
        Map<String ,Object> resultMap = new HashMap<>(6);
        Map<String,Object> map = new HashMap<>(6);
        /**
         * searchText
         * page
         * limit
         * create
         */
        if(templateSearchVo==null){
            templateSearchVo = new TemplateSearchVo();

        }else {
            if(templateSearchVo.getPage()==null){
                templateSearchVo.setPage(1);
            }
            if(templateSearchVo.getLimit()==null){
                templateSearchVo.setLimit(10);
            }

        }
        map.put("index",(templateSearchVo.getPage()-1)*templateSearchVo.getLimit());
        map.put("pageSize",templateSearchVo.getLimit());
        map.put("isCreate",templateSearchVo.getCreate());
        map.put("searchText",templateSearchVo.getSearchText());
        List<TemplateDictVo> templateDictVoList = this.baseMapper.getDictList(map);
        Integer count = this.baseMapper.getDictCount(map);
        resultMap.put("code",0);
        resultMap.put("msg","xxx");
        resultMap.put("data",templateDictVoList);
        resultMap.put("count",count);
        return  resultMap;
    }
}

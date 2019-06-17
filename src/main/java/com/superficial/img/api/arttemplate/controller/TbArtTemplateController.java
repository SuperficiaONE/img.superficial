package com.superficial.img.api.arttemplate.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.arttemplate.vo.ElementVO;
import com.superficial.img.api.arttemplate.vo.TemplateVO;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 定义艺术模板 前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-04-18
 */
@Slf4j
@RestController
public class TbArtTemplateController {
    @Autowired
    private ITbArtTemplateService artTemplateService;
    @Autowired
    private ITbDictService dictService;
    @RequestMapping("/api/tbArtTemplate/save")
    public ResultVO addTemplate(TbArtTemplate tbArtTemplate){
        try {
            if(CommonUtil.isEmpty(tbArtTemplate)){
                return ResultVO.newFail("参数不能为空");
            }
            if(CommonUtil.isEmpty(tbArtTemplate.getTemplateScript())){
                return ResultVO.newFail("模板脚本参数不能为空");
            }
            if(CommonUtil.isEmpty(tbArtTemplate.getTemplateData())){
                return ResultVO.newFail("模板结构数据参数不能为空");
            }
            if(CommonUtil.isEmpty(tbArtTemplate.getTemplateType())){
                return ResultVO.newFail("模板类型参数不能为空");
            }
            if(artTemplateService.selectCount(new EntityWrapper<TbArtTemplate>().eq("template_type",tbArtTemplate.getTemplateType()))>0){
                artTemplateService.update(tbArtTemplate,new EntityWrapper<TbArtTemplate>().eq("template_type",tbArtTemplate.getTemplateType()));
            }else {
                String loginName = JwtHelper.getLoginName();
                artTemplateService.insert(tbArtTemplate
                        .setCreateAt(new Date())
                        .setId(IdWorker.getId())
                        .setUpdateAt(new Date())
                        .setCreateUser(loginName)
                        .setUpdateUser(loginName)
                );
            }

            return ResultVO.newSuccess("保存成功",tbArtTemplate);
        }catch (Exception e){
            log.error("保存模板发生了异常",e);
            return ResultVO.newSuccess("保存模板发生了异常"+e.getMessage());
        }
    }
    @RequestMapping("/api/artTemplate/list")
    public ResultVO getList(String types){
        try {
            if(CommonUtil.isEmpty(types)){
                types="0";
            }
            List<String> list = Arrays.asList( types.split(","));
            List<TbArtTemplate> tbArtTemplateList = artTemplateService.selectList(new EntityWrapper<TbArtTemplate>().in("template_type",list));
            return ResultVO.newSuccess("获取模板数据成功",tbArtTemplateList);
        }catch (Exception e){
            return ResultVO.newError("获取模板数据失败"+e.getMessage());
        }
    }

    @RequestMapping("/api/tbArtTemplate/list")
    public LayUIPage addTemplate(Integer page ,Integer pageSize,
                                @Param("elementId") String elementId,
                                @Param("templateId") String templateId){
        try {
            List<ArtTemplateVo> tbArtTemplateList;
            page = Optional.ofNullable(page).orElse(1);
            pageSize = Optional.ofNullable(pageSize).orElse(10);
            if(page<1){
                page = 1;
            }
            if(pageSize<1){
                pageSize = 10;
            }
                Integer count = artTemplateService.selectCount(  new EntityWrapper<TbArtTemplate>());
                tbArtTemplateList = artTemplateService.getArtTemplateVoList(page,pageSize);
                dictService.changeArtTemplateVoList(tbArtTemplateList);
                LayUIPage layUIPage = new LayUIPage();
                layUIPage.setData(tbArtTemplateList).setCount(count).setCode(0).setMsg("获取模板列表成功");
              return layUIPage;

        }catch (Exception e){
            return new LayUIPage().setCode(-1).setMsg("获取模板列表了异常"+e.getMessage());
        }
    }
    @RequestMapping("/api/templateScript/list")
    public ResultVO getTemplateScriptList(@RequestParam("types") String types){
        try {
            List<String> templateScriptList = artTemplateService.getTemplateScriptList(types);
            return ResultVO.newSuccess("获取模板脚本列表成功",templateScriptList);
        }catch (Exception e){
            log.error("获取模板脚本列表出现了异常",e);
            return ResultVO.newError("获取模板脚本列表出现了异常"+e.getMessage());
        }
    }
}


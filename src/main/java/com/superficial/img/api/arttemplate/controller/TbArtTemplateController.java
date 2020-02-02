package com.superficial.img.api.arttemplate.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.superficial.img.api.arttemplate.service.ITbArtTemplateService;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.arttemplate.vo.TemplateSearchVo;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
            tbArtTemplate = artTemplateService.insertArtTemplate(tbArtTemplate);
            return ResultVO.newSuccess("保存成功",tbArtTemplate);
        }catch (Exception e){
            log.error("保存模板发生了异常",e);
            return ResultVO.newSuccess(e.getMessage());
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
                                @Param("templateId") String templateId ,@Param("useType") String useType){
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
            EntityWrapper<TbArtTemplate> wrapper = new EntityWrapper<>();
            if(!CommonUtil.isEmpty(useType)){
                wrapper.eq("use_type",useType);
            }
            Integer count = artTemplateService.selectCount( wrapper );
                tbArtTemplateList = artTemplateService.getArtTemplateVoList(page,pageSize,useType);
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
    @RequestMapping("/api/tbArtTemplate/delete")
    public ResultVO deleteArtTemplate(String templateId){
        try {
            return ResultVO.newSuccess("");
        }catch (Exception e){
            log.error("删除模板发生错误：",e.getMessage());
            return ResultVO.newError("删除模板发生错误："+e.getMessage());
        }
    }
    @RequestMapping("/api/tbArtTemplate/dictList")
    public Map gerDictList( @Validated TemplateSearchVo templateSearchVo){
       try {
           Map<String ,Object> map = artTemplateService.getDictList(templateSearchVo);
          return  map;
       } catch (Exception e) {
           log.error("", e.getMessage());
           return ResultVO.newError("" + e.getMessage()).toMap();
       }
    }
    @RequestMapping("/api/tbArtTemplate/edit")
    public ResultVO getEditList(String templateId){
        try {
            Map<String ,Object> map = new HashMap<>();
            TbArtTemplate tbArtTemplate = artTemplateService.selectById(templateId);
            List<TbDict> dictList = dictService.selectList(new EntityWrapper<TbDict>()
                    .eq("dict_key", tbArtTemplate.getTemplateType())
                    .eq("dict_type", "template_type"));
            map.put("vo",tbArtTemplate);
            map.put("dict",dictList!=null && dictList.size()>0?dictList.get(0):null);
            return ResultVO.newSuccess(map);
        }catch (Exception e){
            log.error("异常",e);
            return ResultVO.newError(e.getMessage());
        }

    }
}


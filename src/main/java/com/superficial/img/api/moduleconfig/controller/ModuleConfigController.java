package com.superficial.img.api.moduleconfig.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.module.domain.Module;
import com.superficial.img.api.moduleconfig.domain.ModuleConfig;
import com.superficial.img.api.moduleconfig.service.IModuleConfigService;
import com.superficial.img.api.moduleconfig.vo.BackModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.IndexModuleConfigVo;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
@RestController
@RequestMapping("/api/moduleConfig")
public class ModuleConfigController {

    @Autowired
    private IModuleConfigService moduleConfigService;

    @RequestMapping("/list")
    public List<ModuleConfig> getList(){
        return moduleConfigService.selectList(new EntityWrapper<>());
    }

    @RequestMapping("/add")
    public ResultVO add(ModuleConfig module){
        module.setCreateAt(new Date())
                .setModuleConfigId(IdWorker.getId())
                .setUpdateAt(new Date()).setCreateBy(JwtHelper.getLoginName()).
                setUpdateBy(JwtHelper.getLoginName());
        moduleConfigService.insert(module);
        return ResultVO.newSuccess("插入成功",module);
    }
    @RequestMapping("/getSiteModule")
    public List<IndexModuleConfigVo>  getIndexModuleConfigList(String  siteId){
        return moduleConfigService.getIndexModuleConfigList(siteId);
    }
    @RequestMapping("/getBackModuleList")
    public List<BackModuleConfigVo> getBackModuleList(String siteId){
        return moduleConfigService.getBackModuleList(siteId);
    }
}


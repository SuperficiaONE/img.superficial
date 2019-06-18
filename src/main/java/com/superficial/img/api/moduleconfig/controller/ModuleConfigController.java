package com.superficial.img.api.moduleconfig.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.moduleconfig.pojo.ModuleConfig;
import com.superficial.img.api.moduleconfig.service.IModuleConfigService;
import com.superficial.img.api.moduleconfig.vo.BackModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.IndexModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.ModuleConfigVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
                setUpdateBy(JwtHelper.getLoginName()).setSiteId(JwtHelper.getSiteId());
        moduleConfigService.insert(module);
        return ResultVO.newSuccess("插入成功",module);
    }

    @RequestMapping("/save")
    public ResultVO save(ModuleConfig module){
        module.setUpdateAt(new Date()).
                setUpdateBy(JwtHelper.getLoginName());
        moduleConfigService.update(module,new EntityWrapper<ModuleConfig>().eq("module_config_id",module.getModuleConfigId()));
        return ResultVO.newSuccess("插入成功",module);
    }
    @RequestMapping("/getSiteModule")
    public List<IndexModuleConfigVo>  getIndexModuleConfigList(String  siteId){
        if(CommonUtil.isEmpty(siteId)){
            siteId = "0";
        }
        return moduleConfigService.getIndexModuleConfigList(siteId);
    }
    @RequestMapping("/getBackModuleList")
    public List<BackModuleConfigVo> getBackModuleList(String siteId){
        if(CommonUtil.isEmpty(siteId)){
            siteId = "0";
        }
        return moduleConfigService.getBackModuleList(siteId);
    }
    @RequestMapping("/getSiteModuleConfigVo")
    public ModuleConfigVo getModuleConfigVo(String moduleConfigId){
        return moduleConfigService.getModuleConfigVo(moduleConfigId);
    }
}

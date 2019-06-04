package com.superficial.img.api.module.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.module.domain.Module;
import com.superficial.img.api.module.service.IModuleService;
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
@RequestMapping("/api/module")
public class ModuleController {

    @Autowired
    private IModuleService moduleService;
    @RequestMapping("/list")
    public List<Module> getList(){
        return moduleService.selectList(new EntityWrapper<>());
    }
    @RequestMapping("/add")
    public ResultVO add(Module module){
        module.setCreateAt(new Date()).setModuleId(IdWorker.getId()).setUpdateAt(new Date());
        moduleService.insert(module);
        return ResultVO.newSuccess("插入成功",module);
    }
}


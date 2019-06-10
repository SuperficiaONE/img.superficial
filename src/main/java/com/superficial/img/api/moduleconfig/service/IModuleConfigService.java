package com.superficial.img.api.moduleconfig.service;

import com.superficial.img.api.moduleconfig.domain.ModuleConfig;
import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.moduleconfig.vo.BackModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.IndexModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.ModuleConfigVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
public interface IModuleConfigService extends IService<ModuleConfig> {

    List<IndexModuleConfigVo> getIndexModuleConfigList(String siteId);

    List<BackModuleConfigVo> getBackModuleList(String siteId);

    ModuleConfigVo getModuleConfigVo(String moduleConfigId);
}

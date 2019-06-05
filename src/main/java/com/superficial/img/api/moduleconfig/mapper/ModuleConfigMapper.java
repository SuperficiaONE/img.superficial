package com.superficial.img.api.moduleconfig.mapper;

import com.superficial.img.api.moduleconfig.domain.ModuleConfig;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.moduleconfig.vo.BackModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.IndexModuleConfigVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
public interface ModuleConfigMapper extends BaseMapper<ModuleConfig> {

    List<IndexModuleConfigVo> getIndexModuleConfigList(String siteId);

    List<BackModuleConfigVo> getBackModuleList(String siteId);
}

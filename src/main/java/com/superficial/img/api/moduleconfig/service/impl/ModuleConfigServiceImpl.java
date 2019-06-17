package com.superficial.img.api.moduleconfig.service.impl;

import com.superficial.img.api.moduleconfig.pojo.ModuleConfig;
import com.superficial.img.api.moduleconfig.mapper.ModuleConfigMapper;
import com.superficial.img.api.moduleconfig.service.IModuleConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.moduleconfig.vo.BackModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.IndexModuleConfigVo;
import com.superficial.img.api.moduleconfig.vo.ModuleConfigVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
@Service
public class ModuleConfigServiceImpl extends ServiceImpl<ModuleConfigMapper, ModuleConfig> implements IModuleConfigService {

    @Override
    public List<IndexModuleConfigVo> getIndexModuleConfigList(String siteId) {
        return this.baseMapper.getIndexModuleConfigList(siteId);
    }

    @Override
    public List<BackModuleConfigVo> getBackModuleList(String siteId) {
        return this.baseMapper.getBackModuleList(siteId);
    }

    @Override
    public ModuleConfigVo getModuleConfigVo(String moduleConfigId) {
        return this.baseMapper.getModuleConfigVo(moduleConfigId);
    }
}

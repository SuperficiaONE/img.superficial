package com.superficial.img.api.module.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.module.mapper.LayoutConfigMapper;
import com.superficial.img.api.module.pojo.LayoutConfig;
import com.superficial.img.api.module.service.LayoutConfigService;
import org.springframework.stereotype.Service;

@Service
public class LayoutConfigServiceImpl extends ServiceImpl<LayoutConfigMapper, LayoutConfig> implements LayoutConfigService {
}

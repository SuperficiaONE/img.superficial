package com.superficial.img.api.module.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.module.mapper.ModuleMapper;
import com.superficial.img.api.module.pojo.Module;
import com.superficial.img.api.module.service.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements ModuleService {
}

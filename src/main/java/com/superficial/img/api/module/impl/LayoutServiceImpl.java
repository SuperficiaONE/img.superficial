package com.superficial.img.api.module.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.module.mapper.LayoutMapper;
import com.superficial.img.api.module.pojo.Layout;
import com.superficial.img.api.module.service.LayoutService;
import org.springframework.stereotype.Service;

@Service
public class LayoutServiceImpl extends ServiceImpl<LayoutMapper, Layout> implements LayoutService {
}

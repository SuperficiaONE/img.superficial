package com.superficial.img.api.module.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.module.pojo.Module;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ModuleMapper extends BaseMapper<Module> {
}

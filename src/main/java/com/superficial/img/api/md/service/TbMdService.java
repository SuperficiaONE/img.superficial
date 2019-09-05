package com.superficial.img.api.md.service;

import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.md.pojo.TbMd;
import com.superficial.img.api.md.vo.MdSearchVo;
import com.superficial.img.api.md.vo.TbMdVo;

import java.util.Map;

public interface TbMdService  extends IService<TbMd> {
    TbMdVo selectMdVoById(Long mdId);

    Map<String, Object> selectMdVoList(MdSearchVo mdSearchVo);
}

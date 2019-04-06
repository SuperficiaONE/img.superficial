package com.superficial.img.api.dict.service.impl;

import com.superficial.img.api.dict.mapper.TbDictMapper;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.dict.vo.SelectVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
@Service
public class TbDictServiceImpl extends ServiceImpl<TbDictMapper, TbDict> implements ITbDictService {

    @Override
    public List<SelectVO<String>> getSelectList() {
        return this.baseMapper.getSelectList();
    }
}

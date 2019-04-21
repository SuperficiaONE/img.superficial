package com.superficial.img.api.tb.service.impl;

import com.superficial.img.api.tb.vo.ThVO;
import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.api.tb.mapper.TbMapper;
import com.superficial.img.api.tb.pojo.Tb;
import com.superficial.img.api.tb.service.ITbService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 这张表用于数据表格头部使用 服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-04-08
 */
@Service
public class TbServiceImpl extends ServiceImpl<TbMapper, Tb> implements ITbService {

    @Override
    public List<SelectVO> getSelectVOList() {
        return this.baseMapper.getSelectVOList() ;
    }

    @Override
    public List<ThVO> selectTableHeaderVoList(Integer type) {
        return this.baseMapper.selectTableHeaderVoList(type);
    }
}

package com.superficial.img.api.menu.service.impl;

import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.api.menu.mapper.TbMenuMapper;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.menu.service.ITbMenuService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-03-31
 */
@Service
public class TbMenuServiceImpl extends ServiceImpl<TbMenuMapper, TbMenu> implements ITbMenuService {

    @Override
    public List<SelectVO> getSelectVoList(int menuLevel) {
        return this.baseMapper.getSelectVoList(menuLevel);
    }
}

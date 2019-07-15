package com.superficial.img.api.menu.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.superficial.img.api.menu.vo.MenuSearchVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.SelectVO;
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

    @Override
    public LayUIPage getLayPageDataBy(MenuSearchVo menuSearchVo) {

        Integer  page =menuSearchVo.getPage();
        Integer pageSize =menuSearchVo.getLimit();
        if(page<1){
            page = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }

        Wrapper<TbMenu> wrapper = new EntityWrapper<TbMenu>();
        if(!CommonUtil.isEmpty(menuSearchVo.getSearchText())){
            wrapper .like("menu_name", menuSearchVo.getSearchText())
                    .or()
                    .like("url", menuSearchVo.getSearchText());
        }
        if(!CommonUtil.isEmpty(menuSearchVo.getMenuLogin())){
            wrapper .eq("menu_login", menuSearchVo.getMenuLogin());
        }
        if(!CommonUtil.isEmpty(menuSearchVo.getMenuBack())){
            wrapper .eq("menu_back", menuSearchVo.getMenuBack());
        }

        Integer count  = selectCount(wrapper);
        List<TbMenu> menuList = selectList(
                wrapper.last("order by menu_order asc , create_at desc  limit "+(page-1)*pageSize+","+ pageSize)
        );
        LayUIPage layUIPage = new LayUIPage().setCode(0).setMsg("获取成功").setCount(count).setData(menuList);
        return layUIPage;
    }
}

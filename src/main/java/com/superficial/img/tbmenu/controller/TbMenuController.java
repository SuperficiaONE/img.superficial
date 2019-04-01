package com.superficial.img.tbmenu.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.LayUIPage;
import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.tbmenu.pojo.TbMenu;
import com.superficial.img.tbmenu.service.ITbMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-03-31
 */
@Slf4j
@RestController
public class TbMenuController {

    @Autowired
    private ITbMenuService menuService;

    @RequestMapping("/webapi/menu/search")
    public LayUIPage search(String searchText ,Integer needLogin ,Integer isBack, Integer page ,Integer limit){
        try {
            page = CommonUtil.isEmpty(page)?1:page;
            limit = CommonUtil.isEmpty(limit)?10:limit;
            if(page<1){
                page = 1;
            }
            if(limit<1){
                limit = 10;
            }

            Wrapper<TbMenu> wrapper = new EntityWrapper<TbMenu>();
            if(!CommonUtil.isEmpty(searchText)){
                wrapper .like("menu_name", searchText)
                        .or()
                        .like("url", searchText);
            }
            if(!CommonUtil.isEmpty(needLogin)){
                wrapper .eq("need_login", needLogin);
            }
            if(!CommonUtil.isEmpty(isBack)){
                wrapper .eq("is_back", isBack);
            }

            Integer count  = menuService.selectCount(wrapper);
            List<TbMenu> menuList = menuService.selectList(
                    wrapper.last("order by 'order' asc , create_at desc  limit "+(page-1)+","+ limit)
                  );

            return new LayUIPage().setCode(0).setMsg("获取成功").setCount(count).setData(menuList);

        }catch (Exception e){
            log.error("获取菜单链接列表出现了异常",e);
            return new LayUIPage().setCode(-1).setMsg("获取菜单链接列表出现了异常");
        }

    }
    @RequestMapping("/api/menu/add")
    public ResultVO add(TbMenu menu){
        try {
            if(CommonUtil.isEmpty(menu)){
                return ResultVO.newFail("数据不能为空");
            }
            if(CommonUtil.isEmpty(menu.getMenuName())){
                return ResultVO.newFail("菜单名称不能为空");
            }
            if(CommonUtil.isEmpty(menu.getUrl())){
                return ResultVO.newFail("链接不能为空");
            }
            if(CommonUtil.isEmpty(menu.getNeedLogin())){
                menu.setNeedLogin(1);
            }
            if(CommonUtil.isEmpty(menu.getIsBack())){
                menu.setIsBack(0);
            }
            Integer count = menuService.selectCount(new EntityWrapper<TbMenu>()
                    .eq("menu_name",menu.getMenuName())
                    .or()
                    .eq("url",menu.getUrl())
            );
            if(count>0){
                return ResultVO.newFail("添加菜单失败：该链接可能已存在");
            }
            menu.setId(IdWorker.getId()).setCreateAt(new Date()).setUpdateAt(new Date());
            menuService.insert(menu);
            return ResultVO.newSuccess("添加菜单成功",menu);
        }catch (Exception e){
            log.error("添加菜单出现了异常:",e);
            return ResultVO.newError("添加菜单出现了异常");
        }
    }

}


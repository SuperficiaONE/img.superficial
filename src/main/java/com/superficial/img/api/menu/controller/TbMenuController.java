package com.superficial.img.api.menu.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.arttemplate.vo.ElementVO;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.api.menu.vo.MenuSearchVo;
import com.superficial.img.api.tb.service.ITbService;
import com.superficial.img.api.tb.vo.UrlVo;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.*;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.menu.service.ITbMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
    public LayUIPage search(MenuSearchVo menuSearchVo){
        try {
            LayUIPage layUIPage = menuService.getLayPageDataBy(menuSearchVo);
            return   layUIPage;

        }catch (Exception e){
            log.error("获取菜单链接列表出现了异常",e);
            return new  LayUIPage().setMsg("获取菜单链接列表出现了异常").setCode(-1);
        }

    }
    @RequestMapping("/webapi/menu/list")
    public Object getList(String searchText , Integer menuLogin , Integer menuBack, Integer page , Integer limit){
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
            if(!CommonUtil.isEmpty(menuLogin)){
                wrapper .eq("menu_login", menuLogin);
            }
            if(!CommonUtil.isEmpty(menuBack)){
                wrapper .eq("menu_back", menuBack);
            }
            Integer count  = menuService.selectCount(wrapper);
            List<TbMenu> menuList = menuService.selectList(
                    wrapper.last("order by menu_order asc , create_at desc  limit "+(page-1)*limit+","+ limit)
            );
            Map<String,Object> map = new HashMap<>();
            map.put("code",0);
            map.put("msg","xxx");
            map.put("data",menuList);
            map.put("count",count);

            return map;

        }catch (Exception e){
            log.error("获取菜单链接列表出现了异常",e);
            return null;
        }

    }

    @RequestMapping("/webapi/menu/getParentIdSelectVo")
    public  ResultVO getParentIdSelectVo(Integer menuLevel){
        try {
            FormItemSelectVO formItemSelectVO = new FormItemSelectVO();
            formItemSelectVO.setElementId("parentId");
            formItemSelectVO.setList(new ArrayList<>());
            formItemSelectVO.setLabelText("上级菜单");
            if(CommonUtil.isEmpty(menuLevel) || menuLevel == 0){
                return  ResultVO.newSuccess("获取数据成功",formItemSelectVO);
            }
            List<SelectVO> selectVOList = menuService.getSelectVoList(menuLevel-1);
           if( !CommonUtil.isEmpty(selectVOList)){
               formItemSelectVO.setList(selectVOList);
           }
            return  ResultVO.newSuccess("获取数据成功",formItemSelectVO);
        }catch (Exception e){
            log.error("获取数据出现了异常",e);
            return ResultVO.newError("获取数据出现了异常"+e.getMessage());
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
            if(CommonUtil.isEmpty(menu.getMenuLogin())){
                menu.setMenuLogin(1);
            }
            if(CommonUtil.isEmpty(menu.getMenuBack())){
                menu.setMenuBack(0);
            }
            Integer count = menuService.selectCount(new EntityWrapper<TbMenu>()
                    .eq("menu_name",menu.getMenuName())
            );
            if(count>0){
                return ResultVO.newFail("添加菜单失败：该链接可能已存在");
            }
            String loginName = JwtHelper.getLoginName();
            menu.setMenuId(IdWorker.getId()).setCreateAt(new Date()).setUpdateAt(new Date())
                    .setCreateUser(loginName)
                    .setUpdateUser(loginName);
            menuService.insert(menu);
            return ResultVO.newSuccess("添加菜单成功",menu);
        }catch (Exception e){
            log.error("添加菜单出现了异常:",e);
            return ResultVO.newError("添加菜单出现了异常");
        }
    }


}


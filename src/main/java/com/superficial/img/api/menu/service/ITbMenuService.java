package com.superficial.img.api.menu.service;

import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-03-31
 */
public interface ITbMenuService extends IService<TbMenu> {

    List<SelectVO> getSelectVoList(int menuLevel);
}

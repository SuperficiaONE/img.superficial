package com.superficial.img.api.tb.service;

import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.tb.vo.ThVO;
import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.api.tb.pojo.Tb;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 这张表用于数据表格头部使用 服务类
 * </p>
 *
 * @author wxc
 * @since 2019-04-08
 */
public interface ITbService extends IService<Tb> {

    List<SelectVO> getSelectVOList();

    List<ThVO> selectTableHeaderVoList(Integer type);


}

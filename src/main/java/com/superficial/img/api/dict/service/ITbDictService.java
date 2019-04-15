package com.superficial.img.api.dict.service;

import com.superficial.img.api.dict.pojo.TbDict;
import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.common.vo.SelectVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
public interface ITbDictService extends IService<TbDict> {

    List<SelectVO> getSelectList();

    List<SelectVO> getSelectList(String dictTypes);
}

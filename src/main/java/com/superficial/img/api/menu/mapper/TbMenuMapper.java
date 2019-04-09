package com.superficial.img.api.menu.mapper;

import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-03-31
 */
@Mapper
@Repository
public interface TbMenuMapper extends BaseMapper<TbMenu> {

    List<SelectVO> getSelectVoList(int menuLevel);
}

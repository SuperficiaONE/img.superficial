package com.superficial.img.api.dict.mapper;

import com.superficial.img.api.dict.pojo.TbDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.common.vo.SelectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
@Mapper
@Repository
public interface TbDictMapper extends BaseMapper<TbDict> {

    List<SelectVO> getSelectList();

    List<SelectVO> getSelectVOList( @Param("dictTypeList") List<String> dictTypeList);
}

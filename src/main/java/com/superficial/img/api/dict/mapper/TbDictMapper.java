package com.superficial.img.api.dict.mapper;

import com.superficial.img.api.dict.pojo.TbDict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.dict.vo.AddDictVo;
import com.superficial.img.api.dict.vo.TbDictVo;
import com.superficial.img.common.vo.SelectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<SelectVO> getAllSelectList();

    List<AddDictVo> getAddDictList(Map<String, Object> map);

    Integer getAddDictCount(Map<String, Object> map);

    List<TbDict> getDictList(Map<String, Object> map);

    Integer getDictCount(Map<String, Object> map);

    TbDictVo getDictVo(String dictId);

    int updateCanDelete(@Param("dictType") String dictType, @Param("dictKey") Integer dictKey,@Param("canDelete") Integer canDelete);
}

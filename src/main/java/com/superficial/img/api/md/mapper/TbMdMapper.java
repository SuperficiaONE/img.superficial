package com.superficial.img.api.md.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.md.pojo.TbMd;
import com.superficial.img.api.md.vo.TbMdVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TbMdMapper  extends BaseMapper<TbMd> {

    TbMdVo selectMdVoById( @Param("mdId") Long mdId);

    List<TbMdVo> getMdVoList(Map<String, Object> map);

    Integer getMdVoCount(Map<String, Object> map);

    List<TbMdVo> selectMdVoList4Flow(@Param("index") String index,@Param("pageSize") String pageSize);

    void addViewCount(@Param("mdId") Long mdId);
}

package com.superficial.img.api.md.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.md.pojo.TbMdCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface TbMdCategoryMapper  extends BaseMapper<TbMdCategory> {

    List<TbMdCategory> getCategoryList(Map<String, Object> map);

    Integer getCategoryCount(Map<String, Object> map);
}

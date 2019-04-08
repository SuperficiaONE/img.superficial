package com.superficial.img.api.tb.mapper;

import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.api.tb.pojo.Tb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 这张表用于数据表格头部使用 Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-04-08
 */
@Mapper
@Repository
public interface TbMapper extends BaseMapper<Tb> {

    List<SelectVO> getSelectVOList();
}

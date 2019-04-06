package com.superficial.img.api.answer.mapper;

import com.superficial.img.api.answer.pojo.TbAnswer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-03-20
 */
@Mapper
@Repository
public interface TbAnswerMapper extends BaseMapper<TbAnswer> {

}

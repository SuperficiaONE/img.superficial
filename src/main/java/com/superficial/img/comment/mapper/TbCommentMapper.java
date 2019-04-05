package com.superficial.img.comment.mapper;

import com.superficial.img.comment.pojo.TbComment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-03-11
 */
@Mapper
@Repository
public interface TbCommentMapper extends BaseMapper<TbComment> {

}

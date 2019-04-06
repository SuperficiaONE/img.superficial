package com.superficial.img.api.plan.mapper;

import com.superficial.img.api.plan.pojo.TbPlan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-04-05
 */
@Mapper
@Repository
public interface TbPlanMapper extends BaseMapper<TbPlan> {

}

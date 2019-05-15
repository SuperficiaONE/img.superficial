package com.superficial.img.api.plan.mapper;

import com.superficial.img.api.plan.pojo.TbPlan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取 年份列表
     * @return
     */
    List<String> getRangeYear();

    List<TbPlan> getShowListWithStarting(@Param("date") Date date,@Param("s") int s, @Param("pageSize") Integer pageSize);

    List<TbPlan> getShowList(@Param("year") String year, @Param("s") int s, @Param("pageSize") Integer pageSize);

    Integer getShowListWithStartingCount(@Param("date") Date date);

    Integer getShowListCount(@Param("year") String year);
}

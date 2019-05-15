package com.superficial.img.api.plan.service;


import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.plan.pojo.TbPlan;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-04-05
 */
public interface ITbPlanService extends IService<TbPlan> {

    List<String> getRangeYear();

    List<TbPlan> getShowList(String year, Integer page);

    Integer getShowListPages(String year);
}

package com.superficial.img.api.plan.controller;


import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.api.plan.pojo.TbPlan;
import com.superficial.img.api.plan.service.ITbPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-04-05
 */
@Slf4j
@RestController
public class TbPlanController {

    @Autowired
    private ITbPlanService planService;

    @RequestMapping("/api/plan/add")
    public ResultVO addPlan(TbPlan plan){
        try {
            if (CommonUtil.isEmpty(plan)){
                return ResultVO.newFail("没有参数");
            }
            if(CommonUtil.isEmpty(plan.getPlanName())){

            }
            if(CommonUtil.isEmpty(plan.getPlanStartTime())){

            }
            if(CommonUtil.isEmpty(plan.getPlanEndTime())){

            }
            if(CommonUtil.isEmpty(plan.getPlanContent())){

            }

        }catch (Exception e){
            log.error("添加计划出现了异常：",e);
            return ResultVO.newError(e.getMessage());
        }
        return ResultVO.newSuccess("");
    }

}


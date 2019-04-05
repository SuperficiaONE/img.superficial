package com.superficial.img.plan.controller;


import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.plan.pojo.TbPlan;
import com.superficial.img.plan.service.ITbPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
       return ResultVO.newSuccess("");
    }

}


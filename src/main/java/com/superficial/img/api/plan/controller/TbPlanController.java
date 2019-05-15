package com.superficial.img.api.plan.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.plan.vo.FlowResultVO;
import com.superficial.img.common.Cons;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.api.plan.pojo.TbPlan;
import com.superficial.img.api.plan.service.ITbPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.*;

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
                return ResultVO.newFail("缺少参数计划名称");
            }
            if(CommonUtil.isEmpty(plan.getPlanStartTime())){
                return ResultVO.newFail("没有参数开始时间");
            }
            if(CommonUtil.isEmpty(plan.getPlanEndTime())){
                return ResultVO.newFail("没有参数结束时间");
            }
            if(CommonUtil.isEmpty(plan.getPlanContent())){
                return ResultVO.newFail("没有参数计划内容");
            }
            plan.setUpdateUser(JwtHelper.getLoginName())
                    .setUserId(JwtHelper.getUserId())
                    .setCreateUser(JwtHelper.getLoginName())
                    .setCreateAt(new Date())
                    .setUpdateAt(new Date())
                    .setPlanExpectDays(CommonUtil.getDays(plan.getPlanStartTime(),plan.getPlanEndTime()))
                    .setPlanId(IdWorker.getId());
            planService.insert(plan);
            return ResultVO.newSuccess("添加成功");
        }catch (Exception e){
            log.error("添加计划出现了异常：",e);
            return ResultVO.newError(e.getMessage());
        }
    }


    @RequestMapping("/api/plan/list")
    public ResultVO getList(){
        try {

            return ResultVO.newSuccess("成功");
        }catch (Exception e){
            log.error("获取计划列表出现了异常",e);
           return ResultVO.newError(e.getMessage());
        }
    }

    /**
     *  用于 流加载的链接
     * @param year
     * @param page
     * @return
     */
    @RequestMapping("/api/plan/showList")
    public FlowResultVO getShowList(String year, Integer page ){
            List<TbPlan> planList = planService.getShowList(year,page);
            Integer pages = planService.getShowListPages(year);
            Map<String ,Object> map = new HashMap<>();
            map.put("list",planList);
            map.put("pages",planList==null ?0:planList.size());
            return FlowResultVO.newSuccess("成功",pages,map);
    }
    @RequestMapping("/api/plan/showVo")
    public  ResultVO getShowVO(){
        try {
            List<String> titles = planService.getRangeYear();
            titles.add(0,"正在进行的计划");
            Map<String ,Object> map = new HashMap<>();
            map.put("titles",titles);
            return ResultVO.newSuccess("获取成功",map);
        }catch (Exception e){
            log.error("获取计划页面渲染的数据出现了异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }
}


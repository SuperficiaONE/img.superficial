package com.superficial.img.api.plan.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.plan.mapper.TbPlanMapper;
import com.superficial.img.api.plan.pojo.TbPlan;
import com.superficial.img.api.plan.service.ITbPlanService;
import com.superficial.img.common.Cons;
import com.superficial.img.common.tool.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-04-05
 */
@Service
public class TbPlanServiceImpl extends ServiceImpl<TbPlanMapper, TbPlan> implements ITbPlanService {

    @Override
    public List<String> getRangeYear() {
        return this.baseMapper.getRangeYear();
    }

    @Override
    public List<TbPlan> getShowList(String year, Integer page) {
        if(CommonUtil.isEmpty(page)||page<0){
            page = 1;
        }
        Integer pageSize=5;
        if(Cons.YEAR_NOW.equals(year)||CommonUtil.isEmpty(year)){
            return  this.baseMapper.getShowListWithStarting(new Date(),(page-1)*pageSize,pageSize);
        }else {
            return  this.baseMapper.getShowList(year,(page-1)*pageSize,pageSize);
        }
    }

    @Override
    public Integer getShowListPages(String year) {
        Integer pageSize=5;
        Integer count = 0;
        if(Cons.YEAR_NOW.equals(year)|| CommonUtil.isEmpty(year)){
             count = this.baseMapper.getShowListWithStartingCount(new Date());
        }else {
             count = this.baseMapper.getShowListCount(year);
        }

        return CommonUtil.getPages(count,pageSize);
    }
}

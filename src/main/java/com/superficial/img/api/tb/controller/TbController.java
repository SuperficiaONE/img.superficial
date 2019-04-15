package com.superficial.img.api.tb.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.common.vo.FormItemSelectVO;
import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.api.tb.pojo.Tb;
import com.superficial.img.api.tb.service.ITbService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 这张表用于数据表格头部使用 前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-04-08
 */
@Slf4j
@RestController
public class TbController {

    @Autowired
    private ITbService tbService;
    @RequestMapping("/webapi/tb/formSelectVo")
    public ResultVO getFormItemSelectVO(){
        try {

            FormItemSelectVO formItemSelectVO = new FormItemSelectVO();
            formItemSelectVO.setLabelText("表名");
            formItemSelectVO.setElementId("parentId");
            List<SelectVO> baseList = new ArrayList<>();
            baseList.add(new SelectVO().setDictText("无"));
            formItemSelectVO.setList(baseList);
            List<SelectVO> list = tbService.getSelectVOList();
            if( !CommonUtil.isEmpty(list) ){
                formItemSelectVO.getList().addAll(list);
            }
            return  ResultVO.newSuccess("获取数据成功",formItemSelectVO);
        }catch (Exception e){
            log.error("获取数据发生了异常",e);
            return  ResultVO.newError("获取数据发生了异常"+e.getMessage());
        }
    }
    @RequestMapping("/api/tb/add")
    public ResultVO add(Tb tb){
        try {
            if(CommonUtil.isEmpty(tb)){
                return ResultVO.newFail("参数不能为空");
            }
            if(CommonUtil.isEmpty(tb.getTbMean())){
                return ResultVO.newFail("含义不能为空");
            }
            if(CommonUtil.isEmpty(tb.getTbName())){
                return ResultVO.newFail("字段名/表名不能为空");
            }
            if(CommonUtil.isEmpty(tb.getTbType())){
                return ResultVO.newFail("表格类型不能为空");
            }
               Long parentId = null;
              Tb parent = tbService.selectOne(new EntityWrapper<Tb>().isNull("tb_parent_id").eq("tb_type",tb.getTbType()));
              if ( !CommonUtil.isEmpty(parent) ){
                  parentId = parent.getTbParentId();
              }
            tb.setTbId(IdWorker.getId()).setCreateAt(new Date()).setUpdateAt(new Date()).setTbParentId(parentId);
            tbService.insert(tb);
            return ResultVO.newSuccess("插入数据成功",tb);
        }catch (Exception e){
            log.error("添加表含义数据出现了异常",e);
            return ResultVO.newError("添加表含义数据出现了异常"+e.getMessage());
        }
    }
}


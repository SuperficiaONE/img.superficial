package com.superficial.img.api.dict.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
@Slf4j
@RestController
public class TbDictController {

    @Autowired
    private ITbDictService dictService;
    @RequestMapping("/webapi/dict/selectList")
    public ResultVO list(){
        try {
            List<SelectVO<String>> list ;
            list = dictService.getSelectList();
            return  ResultVO.newSuccess("获取字典列表成功",list);
        }catch (Exception e){
            log.info("获取字典列表出现了异常",e);
            return ResultVO.newError("获取字典列表出现了异常"+e.getMessage());
        }
    }
    @RequestMapping("/api/dict/add")
    public  ResultVO add(TbDict tbDict,String chineseText){
        try {
            if(CommonUtil.isEmpty(tbDict)){
                return ResultVO.newFail("参数不能为空");
            }
            if(CommonUtil.isEmpty(tbDict.getDictValue())){
                return ResultVO.newFail("dictValue不能为空");
            }
            // 判断有无 chineseText 有表示插入的数据为 dataType 为 dict_type 或者 dict_value  "dict_type"
            if(CommonUtil.isEmpty(chineseText)){
                if(CommonUtil.isEmpty(tbDict.getDataType())){
                    return ResultVO.newFail("dataType不能为空");
                }
                Integer count= dictService.selectCount(new EntityWrapper<TbDict>().eq("data_type",tbDict.getDataType()));
                tbDict.setDictId(IdWorker.getId())
                        .setDictKey(count)
                        .setUpdateAt(new Date())
                        .setCreateAt(new Date());
                dictService.insert(tbDict);

            }else {
                   if("dict_type".equals(tbDict.getDataType())){
                       // 需要插入两条
                       Integer count = dictService.selectCount(new EntityWrapper<TbDict>().eq("data_type",tbDict.getDataType()));
                       tbDict.setDictId(IdWorker.getId())
                               .setDictKey(count)
                               .setUpdateAt(new Date())
                               .setCreateAt(new Date());
                       dictService.insert(tbDict);
                       tbDict.setDictId(IdWorker.getId())
                               .setDictKey(0)
                               .setDataType(tbDict.getDictValue())
                               .setDictValue(chineseText);
                       dictService.insert(tbDict);
                   }else {
                       tbDict.setDataType(tbDict.getDictValue())
                               .setDictId(IdWorker.getId())
                               .setCreateAt(new Date())
                               .setUpdateAt(new Date())
                               .setDictValue(chineseText)
                               .setDictKey(0);
                       dictService.insert(tbDict);
                   }

            }
            return ResultVO.newSuccess("添加字典成功",tbDict);
        }catch (Exception e){
            log.error("添加字典出现异常",e);
            return  ResultVO.newError("添加字典出现异常:"+e.getMessage());
        }
    }
}


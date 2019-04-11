package com.superficial.img.api.dict.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.api.dict.tool.ConvTool;
import com.superficial.img.api.dict.vo.FormItemSelectVO;
import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


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

    /**
     * 获取option 的数据
     * 废弃 但不删除
     * 通过改造是以下链接 /webapi/dict/formSelectList 初始化 select
     * @return
     */
    @RequestMapping("/webapi/dict/selectList")
    public ResultVO list(){
        try {
            List<SelectVO> list ;
            list = dictService.getSelectList();
            return  ResultVO.newSuccess("获取字典列表成功",list);
        }catch (Exception e){
            log.info("获取字典列表出现了异常",e);
            return ResultVO.newError("获取字典列表出现了异常"+e.getMessage());
        }
    }

    /**
     * 添加字典数据
     * 这个很重要
     * @param tbDict
     * @param chineseText
     * @return
     */
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
                if(CommonUtil.isEmpty(tbDict.getDictType())){
                    return ResultVO.newFail("dataType不能为空");
                }
                Integer count= dictService.selectCount(new EntityWrapper<TbDict>().eq("dict_type",tbDict.getDictType()));
                tbDict.setDictId(IdWorker.getId())
                        .setDictKey(count)
                        .setUpdateAt(new Date())
                        .setCreateAt(new Date());
                dictService.insert(tbDict);

            }else {
                   if("dict_type".equals(tbDict.getDictType())){
                       // 需要插入两条
                       Integer count = dictService.selectCount(new EntityWrapper<TbDict>().eq("dict_type",tbDict.getDictType()));
                       tbDict.setDictId(IdWorker.getId())
                               .setDictKey(count)
                               .setUpdateAt(new Date())
                               .setCreateAt(new Date());
                       dictService.insert(tbDict);
                       tbDict.setDictId(IdWorker.getId())
                               .setDictKey(0)
                               .setDictType(tbDict.getDictValue())
                               .setDictValue(chineseText);
                       dictService.insert(tbDict);
                   }else {
                       tbDict.setDictType(tbDict.getDictValue())
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

    /**
     * 初始化select用也很重要
     * dictKey 0 获取 字典类型
     * dictKey 1 获取 单个
     * 为空获取 列表
     * @param dictTypes
     * @return
     */
    @RequestMapping("/webapi/dict/formSelectList")
    public ResultVO getFormSelectList(@RequestParam("dictTypes")String dictTypes,Integer dictKey,Boolean containAll){
        //dictType 不为空 则是获取 字典表中的 dictKey 为0的数据
        if(CommonUtil.isEmpty(containAll)){
            containAll = false;
        }
        try {
             if(!CommonUtil.isEmpty(dictKey)){
                 if(dictKey == 0){
                     List<SelectVO> list  = dictService.getSelectList();
                     FormItemSelectVO formItemSelectVO = new FormItemSelectVO();
                     formItemSelectVO.setElementId("dictType");
                     formItemSelectVO.setLabelText("字典类型");
                     formItemSelectVO.setList(list);
                     return  ResultVO.newSuccess("获取数据成功",formItemSelectVO);
                 }else if (dictKey == 1){
                     List<SelectVO> list  = dictService.getSelectList(dictTypes);
                     List<FormItemSelectVO> formItemSelectVOList = ConvTool.changeToFormItemSelectVOList(list,containAll);
                     if(CommonUtil.isEmpty(formItemSelectVOList)){
                         return  ResultVO.newSuccess("获取数据成功",formItemSelectVOList);
                     }else {
                         return  ResultVO.newSuccess("获取数据成功",formItemSelectVOList.get(0));
                     }

                 }else {
                     return  ResultVO.newSuccess("获取数据成功",new FormItemSelectVO());
                 }

             }else {
                 List<SelectVO> list  = dictService.getSelectList(dictTypes);
                 List<FormItemSelectVO> formItemSelectVOList = ConvTool.changeToFormItemSelectVOList(list,containAll);
                 return  ResultVO.newSuccess("获取数据成功",formItemSelectVOList);
             }

        }catch (Exception e){
            log.info("获取数据出现异常",e);
            return ResultVO.newError("获取数据出现异常"+e.getMessage());
        }
    }
}


package com.superficial.img.esSearch.controller;

import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.esSearch.service.EsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EsSearchController {
    @Autowired
    private EsSearchService esSearchService;


    @RequestMapping("/api/es/getTest")
    public ResultVO getTest(@RequestParam("type") Integer type,Integer size ,Integer from){
        try {
            if(CommonUtil.isEmpty(size)){
                size = 10;
            }
            if(CommonUtil.isEmpty(from)|| from<0){
                from = 0;
            }
            ResultVO resultVO = ResultVO.newSuccess(esSearchService.getRs(type,from,size));
            return resultVO;
        }catch (Exception e){
            log.error("获取es数据出现了异常",e);
            return ResultVO.newError(e.getMessage());
        }
    }
}

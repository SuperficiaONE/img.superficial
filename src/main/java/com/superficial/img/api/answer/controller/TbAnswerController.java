package com.superficial.img.api.answer.controller;


import com.superficial.img.api.answer.service.ITbAnswerService;
import com.superficial.img.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wxc
 * @since 2019-03-20
 */
@Slf4j
@RestController
@RequestMapping("/api/answer")
public class TbAnswerController {

    @Autowired
    private ITbAnswerService answerService;


    @RequestMapping("/add")
    public ResultVO addAnswer(@RequestParam(name = "content") String content ,@RequestParam("englishContent") String englishContent){
      try {
         Map<String,Object>  map = answerService.addAnswer(content,englishContent);
         return ResultVO.newSuccess("添加成功",map);
      }catch (Exception e){
          log.error("添加答案出现了异常",e);
          return ResultVO.newFail(e.getMessage());
      }

    }
}


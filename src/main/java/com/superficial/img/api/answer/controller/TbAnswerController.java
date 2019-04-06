package com.superficial.img.api.answer.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.vo.ResultVO;
import com.superficial.img.api.answer.pojo.TbAnswer;
import com.superficial.img.api.answer.service.ITbAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
          if(CommonUtil.isBlank(content)){
              return ResultVO.newFail("内容不能为空");
          }
          content = content.trim();
          Integer count=answerService.selectCount(new EntityWrapper<TbAnswer>().eq("content",content));
          if(count>0){
              return ResultVO.newFail("已添加过");
          }
          Long id = IdWorker.getId();
          TbAnswer answer = new TbAnswer()
                  .setAnswerId(id)
                  .setContent(content)
                  .setEnglishContent(englishContent)
                  .setCreateAt(new Date())
                  .setUpdateAt(new Date());
          answerService.insert(answer);
          Map<String,Object> map = new HashMap<>(2);
          map.put("answerId",id);
          return ResultVO.newSuccess("添加成功",map);
      }catch (Exception e){
          log.error("添加答案出现了异常",e);
          return ResultVO.newError(e.getMessage());
      }

    }
}


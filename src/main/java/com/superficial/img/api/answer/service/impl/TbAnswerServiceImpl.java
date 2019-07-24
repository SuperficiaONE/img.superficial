package com.superficial.img.api.answer.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.answer.mapper.TbAnswerMapper;
import com.superficial.img.api.answer.pojo.TbAnswer;
import com.superficial.img.api.answer.service.ITbAnswerService;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-03-20
 */
@Service
public class TbAnswerServiceImpl extends ServiceImpl<TbAnswerMapper, TbAnswer> implements ITbAnswerService {

    @Override
    public Map<String, Object> addAnswer(String content, String englishContent) throws Exception {
        if(CommonUtil.isBlank(content)){
            throw  new Exception("内容不能为空");
        }
        content = content.trim();
        Integer count=selectCount(new EntityWrapper<TbAnswer>().eq("content",content));
        if(count>0){
           throw  new Exception("已添加过");
        }
        String loginName = JwtHelper.getLoginName();
        Long id = IdWorker.getId();
        TbAnswer answer = new TbAnswer()
                .setAnswerId(id)
                .setContent(content)
                .setEnglishContent(englishContent)
                .setCreateAt(new Date())
                .setUpdateAt(new Date())
                .setCreateUser(loginName)
                .setUpdateUser(loginName);
        insert(answer);
        Map<String,Object> map = new HashMap<>(2);
        map.put("answerId",id);
        return map;
    }
}

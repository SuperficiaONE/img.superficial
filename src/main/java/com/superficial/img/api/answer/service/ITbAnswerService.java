package com.superficial.img.api.answer.service;

import com.superficial.img.api.answer.pojo.TbAnswer;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-03-20
 */
public interface ITbAnswerService extends IService<TbAnswer> {

    Map<String, Object> addAnswer(String content, String englishContent) throws Exception;
}

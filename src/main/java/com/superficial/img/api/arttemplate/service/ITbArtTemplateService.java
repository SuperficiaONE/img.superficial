package com.superficial.img.api.arttemplate.service;

import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 定义艺术模板 服务类
 * </p>
 *
 * @author wxc
 * @since 2019-04-18
 */
public interface ITbArtTemplateService extends IService<TbArtTemplate> {

    List<String> getTemplateScriptList(String types);
}

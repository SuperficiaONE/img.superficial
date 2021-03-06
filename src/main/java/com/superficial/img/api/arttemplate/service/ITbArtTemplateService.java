package com.superficial.img.api.arttemplate.service;

import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.arttemplate.vo.TemplateSearchVo;

import java.util.List;
import java.util.Map;

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

    List<ArtTemplateVo> getArtTemplateVoList(Integer page, Integer pageSize,String useType);

    TbArtTemplate insertArtTemplate(TbArtTemplate tbArtTemplate) throws Exception;

    Map<String, Object> getDictList(TemplateSearchVo templateSearchVo);
}

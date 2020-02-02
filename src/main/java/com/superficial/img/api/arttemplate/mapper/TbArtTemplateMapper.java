package com.superficial.img.api.arttemplate.mapper;

import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.arttemplate.vo.TemplateDictVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定义艺术模板 Mapper 接口
 * </p>
 *
 * @author wxc
 * @since 2019-04-18
 */
public interface TbArtTemplateMapper extends BaseMapper<TbArtTemplate> {

    List<String> getTemplateScriptList(@Param("typeList") List<String> typeList);

    List<ArtTemplateVo> getArtTemplateVoList(@Param("from") Integer from,
                                             @Param("pageSize") Integer pageSize,
                                             @Param("useType") String useType);


    List<TemplateDictVo> getDictList(Map map);

    Integer getDictCount(Map map);
}

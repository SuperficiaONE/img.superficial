package com.superficial.img.api.dict.service;

import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.dict.pojo.TbDict;
import com.baomidou.mybatisplus.service.IService;
import com.superficial.img.api.dict.vo.AddDictSearchVo;
import com.superficial.img.api.dict.vo.TbDictVo;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.tb.vo.UrlVo;
import com.superficial.img.common.vo.SelectVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
public interface ITbDictService extends IService<TbDict> {

    List<SelectVO> getSelectList();
    List<SelectVO> getAllSelectList();

    List<SelectVO> getSelectList(String dictTypes);

    List<Map<String, UrlVo>> changeMenuList(List<TbMenu> menuList);

    void changeArtTemplateVoList(List<ArtTemplateVo> tbArtTemplateList);

    TbDict addDict(TbDict tbDict, String chineseText) throws Exception;

    Map getAddDictList(AddDictSearchVo addDictSearchVo);

    Map getDictList(AddDictSearchVo addDictSearchVo);

    TbDictVo getDictVo(String dictId);

    int  updateCanDelete(String dictType,Integer dictKey,Integer canDelete);
}

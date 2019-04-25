package com.superficial.img.api.dict.service.impl;

import com.superficial.img.api.dict.mapper.TbDictMapper;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.tb.vo.UrlVo;
import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.common.tool.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
@Service
public class TbDictServiceImpl extends ServiceImpl<TbDictMapper, TbDict> implements ITbDictService {

    @Override
    public List<SelectVO> getSelectList() {
        return this.baseMapper.getSelectList();
    }

    @Override
    public List<SelectVO> getAllSelectList() {
        return this.baseMapper.getAllSelectList();
    }

    @Override
    public List<SelectVO> getSelectList(String dictTypes) {
        if(CommonUtil.isEmpty(dictTypes)){
            return  null;
        }
       String[] dictType = dictTypes.split(",");
        for (int i = 0; i < dictType.length; i++) {
            dictType[i] = CommonUtil.humpToLine(dictType[i]);
        }
        List<String> dictTypeList = Arrays.asList(dictType);

        return this.baseMapper.getSelectVOList(dictTypeList);
    }

    @Override
    public List<Map<String, UrlVo>> changeMenuList(List<TbMenu> menuList) {
        List<SelectVO> selectList = getAllSelectList();
        Map<String, List<SelectVO>> selectVOMap = selectList.stream().collect(Collectors.groupingBy(SelectVO::getDictType));
        List<Map<String, UrlVo>> list = new ArrayList<>();
        for (TbMenu  menu:menuList) {
          Map<String ,Object> objectMap = CommonUtil.ConvertObjToMap(menu);
            Map<String, UrlVo> rm = new HashMap<>();
            for (Map.Entry<String, Object> stringObjectEntry : objectMap.entrySet()) {
                List<SelectVO> selectVOList = selectVOMap.get(CommonUtil.humpToLine(stringObjectEntry.getKey()));
              UrlVo urlVo = new UrlVo();
            if("menuName".equals(stringObjectEntry.getKey())) {
                  urlVo.setTitle(menu.getMenuName()).setUrl(CommonUtil.isBlank(menu.getUrl())?null:menu.getUrl());
              }else if(selectVOList != null){
                for (SelectVO selectVO : selectVOList) {
                    if(selectVO.getDictValue().equals(stringObjectEntry.getValue()+"")){
                        urlVo.setTitle(selectVO.getDictText());
                    }
                }
              }else {
                urlVo.setTitle(stringObjectEntry.getValue()==null?"":stringObjectEntry.getValue().toString());
            }
                rm.put(stringObjectEntry.getKey(),urlVo);
            }
            rm.put("play",new UrlVo().setTitle("访问").setUrl(menu.getUrl()));
            list.add(rm);
        }
        return list;
    }
}

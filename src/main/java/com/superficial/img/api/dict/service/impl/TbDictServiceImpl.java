package com.superficial.img.api.dict.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.superficial.img.api.arttemplate.vo.ArtTemplateVo;
import com.superficial.img.api.dict.mapper.TbDictMapper;
import com.superficial.img.api.dict.pojo.TbDict;
import com.superficial.img.api.dict.service.ITbDictService;
import com.superficial.img.api.dict.vo.AddDictSearchVo;
import com.superficial.img.api.dict.vo.AddDictVo;
import com.superficial.img.api.menu.pojo.TbMenu;
import com.superficial.img.api.tb.vo.UrlVo;
import com.superficial.img.common.tool.CommonUtil;
import com.superficial.img.common.tool.JwtHelper;
import com.superficial.img.common.vo.SelectVO;
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

    @Override
    public void changeArtTemplateVoList(List<ArtTemplateVo> tbArtTemplateList) {
        List<SelectVO> selectList = this.getSelectList("template_type");
        if(!CommonUtil.isEmpty(selectList) && !CommonUtil.isEmpty(tbArtTemplateList)){
            Map<String, SelectVO> collect = selectList.stream().collect(Collectors.toMap(SelectVO::getDictValue, t -> t));
            for (int i = 0; i <tbArtTemplateList.size() ; i++) {
                SelectVO selectVO = collect.get(tbArtTemplateList.get(i).getTemplateType()+"");
                tbArtTemplateList.get(i).setTemplateTypeText(selectVO!=null?selectVO.getDictText():"");
            }
        }
    }

    @Override
    public TbDict addDict(TbDict tbDict, String chineseText) throws Exception {
        if(CommonUtil.isEmpty(tbDict)){
            throw  new Exception("参数不能为空");
        }
        if(CommonUtil.isEmpty(tbDict.getDictValue())){
            throw  new Exception("dictValue不能为空");
        }
        String loginName = JwtHelper.getLoginName();
        // 判断有无 chineseText 有表示插入的数据为 dataType 为 dict_type 或者 dict_value  "dict_type"
        if(CommonUtil.isEmpty(chineseText)){
            if(CommonUtil.isEmpty(tbDict.getDictType())){
                throw  new Exception("dataType不能为空");
            }
            Integer count= selectCount(new EntityWrapper<TbDict>().eq("dict_type",tbDict.getDictType()));
            tbDict.setDictId(IdWorker.getId())
                    .setDictKey(count)
                    .setUpdateAt(new Date())
                    .setCreateAt(new Date())
                    .setCreateUser(loginName)
                    .setUpdateUser(loginName);
            insert(tbDict);

        }else {
            if("dict_type".equals(tbDict.getDictType())){
                // 需要插入两条
                Integer count = selectCount(new EntityWrapper<TbDict>().eq("dict_type",tbDict.getDictType()));
                tbDict.setDictId(IdWorker.getId())
                        .setDictKey(count)
                        .setUpdateAt(new Date())
                        .setCreateAt(new Date());
                insert(tbDict);
                tbDict.setDictId(IdWorker.getId())
                        .setDictKey(0)
                        .setDictType(tbDict.getDictValue())
                        .setDictValue(chineseText)
                        .setCreateUser(loginName)
                        .setUpdateUser(loginName);
                insert(tbDict);
            }else {
                tbDict.setDictType(tbDict.getDictValue())
                        .setDictId(IdWorker.getId())
                        .setCreateAt(new Date())
                        .setUpdateAt(new Date())
                        .setDictValue(chineseText)
                        .setDictKey(0)
                        .setCreateUser(loginName)
                        .setUpdateUser(loginName);
                insert(tbDict);
            }

        }
        return tbDict;
    }


    /**
     * Map 中需要
     * code  0 为成功
     * msg   异常显示
     * data   数据
     * count  总数
     *
     * @param addDictSearchVo
     * @return
     */
    @Override
    public Map getAddDictList(AddDictSearchVo addDictSearchVo) {
        Map<String ,Object> resultMap = new HashMap<>(6);
        Map<String,Object> map = new HashMap<>(6);
        if(addDictSearchVo==null){
            addDictSearchVo = new AddDictSearchVo();

        }else {
            if(addDictSearchVo.getPage()==null){
                addDictSearchVo.setPage(1);
            }
            if(addDictSearchVo.getLimit()==null){
                addDictSearchVo.setLimit(10);
            }

        }
        map.put("index",(addDictSearchVo.getPage()-1)*addDictSearchVo.getLimit());
        map.put("pageSize",addDictSearchVo.getLimit());
        map.put("searchText",addDictSearchVo.getSearchText());
        List<AddDictVo> templateDictVoList = this.baseMapper.getAddDictList(map);
        Integer count = this.baseMapper.getAddDictCount(map);
        resultMap.put("code",0);
        resultMap.put("msg","xxx");
        resultMap.put("data",templateDictVoList);
        resultMap.put("count",count);
        return  resultMap;

    }

    @Override
    public Map getDictList(AddDictSearchVo addDictSearchVo) {
        Map<String ,Object> resultMap = new HashMap<>(6);
        Map<String,Object> map = new HashMap<>(6);
        if(addDictSearchVo==null){
            addDictSearchVo = new AddDictSearchVo();

        }else {
            if(addDictSearchVo.getPage()==null){
                addDictSearchVo.setPage(1);
            }
            if(addDictSearchVo.getLimit()==null){
                addDictSearchVo.setLimit(10);
            }

        }
        map.put("index",(addDictSearchVo.getPage()-1)*addDictSearchVo.getLimit());
        map.put("pageSize",addDictSearchVo.getLimit());
        map.put("searchText",addDictSearchVo.getSearchText());
        List<TbDict> templateDictVoList = this.baseMapper.getDictList(map);
        Integer count = this.baseMapper.getDictCount(map);
        resultMap.put("code",0);
        resultMap.put("msg","xxx");
        resultMap.put("data",templateDictVoList);
        resultMap.put("count",count);
        return  resultMap;
    }
}

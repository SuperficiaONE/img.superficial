package com.superficial.img.api.dict.tool;

import com.superficial.img.common.vo.FormItemSelectVO;
import com.superficial.img.common.vo.SelectVO;
import com.superficial.img.common.tool.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConvTool {

    public static List<FormItemSelectVO> changeToFormItemSelectVOList(List<SelectVO> list) {
        return  changeToFormItemSelectVOList(list,false);
    }

    /**
     *
     * @param list
     * @param containAll
     * @return
     */
    public static List<FormItemSelectVO> changeToFormItemSelectVOList(List<SelectVO> list,Boolean containAll) {
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        Map<String,FormItemSelectVO> map = new HashMap<>();
        List<FormItemSelectVO> formItemSelectVOList = new ArrayList<>();
        for (SelectVO selectVO :list) {
            FormItemSelectVO formItemSelectVO =  map.get(selectVO.getDictType());
            if(CommonUtil.isEmpty(formItemSelectVO)){
                // 构建一个formItemSelectVO
                formItemSelectVO = new FormItemSelectVO();
                formItemSelectVO.setElementId(CommonUtil.lineToHump(selectVO.getDictType()));
                formItemSelectVO.setLabelText(selectVO.getDictText());
                formItemSelectVO.setList(new ArrayList<>());
                formItemSelectVOList.add(formItemSelectVO);
                map.put(selectVO.getDictType(),formItemSelectVO);
                continue;
            }
            if(containAll && formItemSelectVO.getList().size()==0){
                SelectVO allSelectVO = new SelectVO().setDictText("全部").setDictValue("");
                formItemSelectVO.getList().add(allSelectVO);
            }
            formItemSelectVO.getList().add(selectVO);

        }
        return formItemSelectVOList;
    }
}

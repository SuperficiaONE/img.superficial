package com.superficial.img.api.dict.tool;

import com.superficial.img.api.dict.vo.FormItemSelectVO;
import com.superficial.img.api.dict.vo.SelectVO;
import com.superficial.img.common.tool.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvTool {

    public static List<FormItemSelectVO> changeToFormItemSelectVOList(List<SelectVO<String>> list) {
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        Map<String,FormItemSelectVO> map = new HashMap<>();
        List<FormItemSelectVO> formItemSelectVOList = new ArrayList<>();
        for (SelectVO selectVO :list) {
            FormItemSelectVO formItemSelectVO =  map.get(selectVO.getDictType());

        }
        return formItemSelectVOList;
    }
}

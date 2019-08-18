package com.superficial.img.api.dict.vo;

import com.superficial.img.common.vo.PageVo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddDictSearchVo extends PageVo {
    /**
     * 字典名称
     */
    private String searchText;
}

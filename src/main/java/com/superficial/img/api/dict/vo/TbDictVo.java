package com.superficial.img.api.dict.vo;

import com.superficial.img.api.dict.pojo.TbDict;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TbDictVo extends TbDict {
    /**
     * false 不能被删除
     * true 能被删除
     */
    private String chineseText;
}

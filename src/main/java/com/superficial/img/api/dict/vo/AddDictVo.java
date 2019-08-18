package com.superficial.img.api.dict.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDictVo {
    /**
     * 字典表id
     */
    private String dictId;

    /**
     *字典的key key为0表示这个type的中文含义
     */
    private String dictValue;

    /**
     *字典类型
     */
    private String dictType;

    /**
     * 该类型的字典数量
     */
    private  Integer dictCount;
}

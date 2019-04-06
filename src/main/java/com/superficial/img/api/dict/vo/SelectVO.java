package com.superficial.img.api.dict.vo;

import lombok.Data;

@Data
public class SelectVO<T> {
    T dictValue ;
    String dictText ;

    public SelectVO setDictValue(T dictValue) {
        this.dictValue = dictValue;
        return this;
    }

    public SelectVO setDictText(String dictText) {
        this.dictText = dictText;
        return this;
    }
}

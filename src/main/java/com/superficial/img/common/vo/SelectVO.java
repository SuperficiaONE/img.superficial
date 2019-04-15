package com.superficial.img.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SelectVO {
    String dictValue ;
    String dictText ;
    @JsonIgnore
    String dictType ;

    Boolean selected = false;

    public SelectVO setDictType(String dictType) {
        this.dictType = dictType;
        return this;
    }

    public SelectVO setDictValue(String dictValue) {
        this.dictValue = dictValue;
        return this;
    }

    public SelectVO setDictText(String dictText) {
        this.dictText = dictText;
        return this;
    }
}

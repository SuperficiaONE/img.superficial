package com.superficial.img.api.tb.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ThVO {
    private String field;
    private String title;

    public ThVO setField(String field) {
        this.field = field;
        return this;
    }

    public ThVO setTitle(String title) {
        this.title = title;
        return this;
    }
}

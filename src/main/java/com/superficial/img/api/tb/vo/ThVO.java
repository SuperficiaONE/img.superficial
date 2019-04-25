package com.superficial.img.api.tb.vo;

import lombok.Data;

@Data
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

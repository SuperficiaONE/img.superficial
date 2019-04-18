package com.superficial.img.api.tb.vo;

import lombok.Data;

@Data
public class TableHeaderVO {
    private String field;
    private String title;

    public TableHeaderVO setField(String field) {
        this.field = field;
        return this;
    }

    public TableHeaderVO setTitle(String title) {
        this.title = title;
        return this;
    }
}

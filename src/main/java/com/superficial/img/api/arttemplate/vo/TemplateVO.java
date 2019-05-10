package com.superficial.img.api.arttemplate.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateVO<T> {
    private String templateId;
    T data;

    public TemplateVO setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public TemplateVO setData(T data) {
        this.data = data;
        return this;
    }
}

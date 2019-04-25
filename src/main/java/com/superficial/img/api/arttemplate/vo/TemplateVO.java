package com.superficial.img.api.arttemplate.vo;

import lombok.Data;

@Data
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

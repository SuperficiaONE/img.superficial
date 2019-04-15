package com.superficial.img.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class FormItemSelectVO {
    String elementId;
    String labelText;
    List<SelectVO> list;


    public FormItemSelectVO setElementId(String elementId) {
        this.elementId = elementId;
        return this;
    }

    public FormItemSelectVO setLabelText(String labelText) {
        this.labelText = labelText;
        return this;
    }

    public FormItemSelectVO setList(List<SelectVO> list) {
        this.list = list;
        return this;
    }
}

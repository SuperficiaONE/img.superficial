package com.superficial.img.api.dict.vo;

import lombok.Data;

import java.util.List;

@Data
public class FormItemSelectVO {
    String elementId;
    String labelText;
    List<SelectVO> list;
}

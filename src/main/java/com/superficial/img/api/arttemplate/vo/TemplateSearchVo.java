package com.superficial.img.api.arttemplate.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateSearchVo {
    private String searchText;
    private Integer page =1;
    private Integer limit=10;
    private Boolean create;
}

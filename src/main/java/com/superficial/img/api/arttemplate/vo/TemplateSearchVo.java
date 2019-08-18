package com.superficial.img.api.arttemplate.vo;

import com.superficial.img.common.vo.PageVo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TemplateSearchVo extends PageVo {
    private String searchText;
    private Boolean create;
}

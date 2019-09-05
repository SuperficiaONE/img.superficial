package com.superficial.img.api.md.vo;

import com.superficial.img.common.vo.PageVo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MdCategorySearchVo extends PageVo {
    private String searchText;
}

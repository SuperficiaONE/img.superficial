package com.superficial.img.api.md.vo;

import com.superficial.img.api.md.pojo.TbMd;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TbMdVo extends TbMd {
    /**
     * 文档分类名称
     */
    private String mdCategoryName;
}

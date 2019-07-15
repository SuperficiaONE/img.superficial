package com.superficial.img.common.vo;

import lombok.Getter;

@Getter
public class PageVo  {
    Integer page = 1 ;
    Integer limit = 10;

    public PageVo setPage(Integer page) {
        this.page = page;
        return this;
    }

    public PageVo setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
}

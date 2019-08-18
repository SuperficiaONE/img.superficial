package com.superficial.img.common.vo;

import lombok.Getter;

@Getter
public class PageVo  {
    /**
     * layui 当前页
     */
    Integer page = 1 ;

    /**
     * layui 页容量
     */
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

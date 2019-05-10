package com.superficial.img.api.tb.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlVo {
    private String title;
    private String url;


    public UrlVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public UrlVo setUrl(String url) {
        this.url = url;
        return this;
    }
}

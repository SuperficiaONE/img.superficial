package com.superficial.img.api.arttemplate.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElementVO<T> {
    private String id;
    private TemplateVO<T> templateVO;
    private String url;
    private Boolean page;

    public ElementVO setPage(Boolean page) {
        this.page = page;
        return this;
    }

    public ElementVO setId(String id) {
        this.id = id;
        return this;
    }

    public ElementVO setTemplateVO(TemplateVO<T> templateVO) {
        this.templateVO = templateVO;
        return this;
    }

    public ElementVO setUrl(String url) {
        this.url = url;
        return this;
    }



    public static ElementVO<Object> newElementVO(String id, String templateId , Object data, String url, Boolean page){
        return new ElementVO<>()
                .setTemplateVO(new TemplateVO<>()
                .setTemplateId(templateId)
                .setData(data))
                .setPage(page).setUrl(url).setId(id);
    }

    public static  ElementVO<Object> newElementVO(String id,String templateId,Object data){
        return  newElementVO(id,templateId,data,null,null);
    }
}

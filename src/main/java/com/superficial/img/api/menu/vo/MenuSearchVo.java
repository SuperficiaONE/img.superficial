package com.superficial.img.api.menu.vo;

import com.superficial.img.common.vo.PageVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuSearchVo extends PageVo {


    /**
     *
     */
    String searchText;

    /**
     *
     */
    String templateId;

    /**
     *
     */
    Integer menuLogin ;

    /**
     *
     */
    Integer menuBack;

    public MenuSearchVo setSearchText(String searchText) {
        this.searchText = searchText;
        return this;
    }

    public MenuSearchVo setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public MenuSearchVo setMenuLogin(Integer menuLogin) {
        this.menuLogin = menuLogin;
        return this;
    }

    public MenuSearchVo setMenuBack(Integer menuBack) {
        this.menuBack = menuBack;
        return this;
    }
}

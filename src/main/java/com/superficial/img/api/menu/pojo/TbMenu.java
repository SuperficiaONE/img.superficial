package com.superficial.img.api.menu.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-03-31
 */
@Setter
@Getter
public class TbMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;

    private Long parentId;

    private String menuName;

    /**
     * 1.菜单(用作展开) 2.页面  3.按钮
     * 值关系存在dict表中
     */
    private Integer menuLevel;
    /**
     * 用作排序
     */
    private Integer menuOrder;
    private String url;
    /**
     * 是否需要登录: 1.需要登录 2.不需要
     */
    private Integer menuLogin;
    /**
     * 是否是后台菜单:1.是 2.不是
     */
    private Integer menuBack;

    private Date createAt;

    private Date updateAt;

    private  String updateUser;

    private String createUser;

    public TbMenu setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TbMenu setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public TbMenu setMenuId(Long menuId) {
        this.menuId = menuId;
        return this;
    }

    public TbMenu setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public TbMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public TbMenu setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
        return this;
    }

    public TbMenu setMenuLogin(Integer menuLogin) {
        this.menuLogin = menuLogin;
        return this;
    }

    public TbMenu setMenuBack(Integer menuBack) {
        this.menuBack = menuBack;
        return this;
    }

    public TbMenu setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
        return this;
    }

    public TbMenu setUrl(String url) {
        this.url = url;
        return this;
    }



    public TbMenu setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbMenu setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

package com.superficial.img.menu.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

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
@Data
public class TbMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long menuId;

    private Long parentId;

    private String menuName;

    /**
    * 1.页面 2.按钮
    */
    private Integer menuType;
    /**
     * 目前只设计两级
     */
    private Integer menuLevel;
    /**
     * 用作排序
     */
    private Integer menuOrder;
    private String url;
    /**
     * 是否需要登录:0.不需要 1.需要登录
     */
    private Integer needLogin;
    /**
     * 是否是后台菜单:0.不是 1.是
     */
    private Integer isBack;

    private Date createAt;

    private Date updateAt;

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

    public TbMenu setMenuType(Integer menuType) {
        this.menuType = menuType;
        return this;
    }

    public TbMenu setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
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

    public TbMenu setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
        return this;
    }

    public TbMenu setIsBack(Integer isBack) {
        this.isBack = isBack;
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

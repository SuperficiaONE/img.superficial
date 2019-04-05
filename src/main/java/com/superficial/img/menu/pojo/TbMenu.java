package com.superficial.img.menu.pojo;

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

    private Long id;
    private String menuName;
    /**
     * 用作排序
     */
    private Integer order;
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

    public TbMenu setId(Long id) {
        this.id = id;
        return this;
    }

    public TbMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public TbMenu setOrder(Integer order) {
        this.order = order;
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

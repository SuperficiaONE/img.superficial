package com.superficial.img.tbmenu.pojo;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(Integer needLogin) {
        this.needLogin = needLogin;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "TbMenu{" +
        ", id=" + id +
        ", menuName=" + menuName +
        ", order=" + order +
        ", url=" + url +
        ", needLogin=" + needLogin +
        ", isBack=" + isBack +
        ", createAt=" + createAt +
        ", updateAt=" + updateAt +
        "}";
    }
}

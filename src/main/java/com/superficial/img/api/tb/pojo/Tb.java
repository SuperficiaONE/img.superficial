package com.superficial.img.api.tb.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 这张表用于数据表格头部使用
 * </p>
 *
 * @author wxc
 * @since 2019-04-08
 */
@Setter
@Getter
public class Tb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long tbId;
    private Integer tbType;
    private Long tbParentId;
    /**
     * 字段含义/表的含义
     */
    private String tbMean;
    /**
     * 字段名称
     */
    private String tbName;
    private Date createAt;
    private Date updateAt;
    private  Integer tbOrder;
    private String createUser;
    private String updateUser;

    public Tb setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public Tb setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public Tb setTbOrder(Integer tbOrder) {
        this.tbOrder = tbOrder;
        return this;
    }

    public Tb setTbId(Long tbId) {
        this.tbId = tbId;
        return this;
    }

    public Tb setTbType(Integer tbType) {
        this.tbType = tbType;
        return this;
    }

    public Tb setTbParentId(Long tbParentId) {
        this.tbParentId = tbParentId;
        return this;
    }

    public Tb setTbMean(String tbMean) {
        this.tbMean = tbMean;
        return this;
    }

    public Tb setTbName(String tbName) {
        this.tbName = tbName;
        return this;
    }

    public Tb setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Tb setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

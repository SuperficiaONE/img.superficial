package com.superficial.img.api.dict.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-04-06
 */
@Setter
@Getter
public class TbDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    @TableId
    private Long dictId;
    /**
     * 字典的值
     */
    private String dictValue;
    /**
     * 字典的key
     */
    private Integer dictKey;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;

    private  String updateUser;

    private String createUser;

    public TbDict setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TbDict setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public TbDict setDictId(Long dictId) {
        this.dictId = dictId;
        return this;
    }

    public TbDict setDictValue(String dictValue) {
        this.dictValue = dictValue;
        return this;
    }

    public TbDict setDictKey(Integer dictKey) {
        this.dictKey = dictKey;
        return this;
    }

    public TbDict setDictType(String dictType) {
        this.dictType = dictType;
        return this;
    }

    public TbDict setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbDict setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

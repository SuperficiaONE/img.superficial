package com.superficial.img.api.answer.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
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
 * @since 2019-03-20
 */
@Setter
@Getter
public class TbAnswer implements Serializable {

    @TableId
    private Long answerId;

    private String content;

    private String englishContent;

    @TableField(fill= FieldFill.INSERT)
    private Date createAt;

    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateAt;

    private  String updateUser;

    private String createUser;

    public TbAnswer setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TbAnswer setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public TbAnswer setAnswerId(Long answerId) {
        this.answerId = answerId;
        return this;
    }

    public TbAnswer setContent(String content) {
        this.content = content;
        return this;
    }

    public TbAnswer setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
        return this;
    }

    public TbAnswer setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbAnswer setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

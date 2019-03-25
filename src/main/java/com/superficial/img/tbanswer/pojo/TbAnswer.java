package com.superficial.img.tbanswer.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
@Data
public class TbAnswer implements Serializable {

    @TableId
    private Long answerId;

    private String content;

    private String englishContent;

    @TableField(fill= FieldFill.INSERT)
    private Date createAt;

    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateAt;

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

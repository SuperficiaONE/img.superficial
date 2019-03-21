package com.superficial.img.tbcomment.pojo;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-03-11
 */
@Data
public class TbComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long commentId;
    private Long parentId;
    private String content;
    private Long userId;
    private String userName;
    private Long picId;
    private Date createAt;
    private Date updateAt;
    private Integer likeCount;
    private Integer dislikeCount;

    public TbComment setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public TbComment setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public TbComment setContent(String content) {
        this.content = content;
        return this;
    }

    public TbComment setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TbComment setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public TbComment setPicId(Long picId) {
        this.picId = picId;
        return this;
    }

    public TbComment setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbComment setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public TbComment setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public TbComment setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
        return this;
    }
}

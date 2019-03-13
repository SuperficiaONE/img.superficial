package com.superficial.img.tbComment.domain;

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

}

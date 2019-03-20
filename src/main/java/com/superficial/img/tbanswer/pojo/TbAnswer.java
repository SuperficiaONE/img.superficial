package com.superficial.img.tbanswer.pojo;

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

    private static final long serialVersionUID = 1L;

    private Long answerId;
    private String content;
    private String englishContent;
    private Date createAt;
    private Date updateAt;

}

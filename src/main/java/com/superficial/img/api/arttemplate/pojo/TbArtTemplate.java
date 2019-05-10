package com.superficial.img.api.arttemplate.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 定义艺术模板
 * </p>
 *
 * @author wxc
 * @since 2019-04-18
 */
@Setter
@Getter
public class TbArtTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer templateType;
    private String templateScript;
    /**
     * 模板需要的数据结构
     */
    private String tempalteData;
    private Date createAt;
    private Date updateAt;
    private  String updateUser;

    private String createUser;

    public TbArtTemplate setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TbArtTemplate setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public TbArtTemplate setId(Long id) {
        this.id = id;
        return this;
    }

    public TbArtTemplate setTemplateType(Integer templateType) {
        this.templateType = templateType;
        return this;
    }

    public TbArtTemplate setTemplateScript(String templateScript) {
        this.templateScript = templateScript;
        return this;
    }

    public TbArtTemplate setTempalteData(String tempalteData) {
        this.tempalteData = tempalteData;
        return this;
    }

    public TbArtTemplate setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbArtTemplate setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

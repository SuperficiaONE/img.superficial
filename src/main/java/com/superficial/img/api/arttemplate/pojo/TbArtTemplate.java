package com.superficial.img.api.arttemplate.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
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

    @TableId
    private Long id;
    private Integer templateType;
    private String templateScript;
    /**
     * 模板需要的数据结构
     */
    private String templateData;
    private  String templateElements;
    private Date createAt;
    private Date updateAt;
    private  String updateUser;
    private String beforeScript;
    private String afterScript;
    private String createUser;
    /**
     * 0.普通模板 1.组件
     */
    private  Integer useType;

    public TbArtTemplate setUseType(Integer useType) {
        this.useType = useType;
        return this;
    }

    public TbArtTemplate setTemplateElements(String templateElements) {
        this.templateElements = templateElements;
        return this;
    }

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

    public TbArtTemplate setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
        return this;
    }

    public TbArtTemplate setAfterScript(String afterScript) {
        this.afterScript = afterScript;
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

    public TbArtTemplate setTemplateData(String templateData) {
        this.templateData = templateData;
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

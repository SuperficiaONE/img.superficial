package com.superficial.img.api.module.domain;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-06-04
 */
@Data
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long moduleId;
    private String moduleTitle;
    private String modulePreviewImg;
    private String moduleJson;
    private String moduleDataTemplateJson;
    private String moduleData;

    private Integer moduleType;

    public Module setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
        return this;
    }

    public Module setModuleData(String moduleData) {
        this.moduleData = moduleData;
        return this;
    }



    private Date createAt;
    private Date updateAt;



    public Module setModuleId(Long moduleId) {
        this.moduleId = moduleId;
        return this;
    }

    public Module setModulePreviewImg(String modulePreviewImg) {
        this.modulePreviewImg = modulePreviewImg;
        return this;
    }

    public Module setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
        return this;
    }

    public Module setModuleJson(String moduleJson) {
        this.moduleJson = moduleJson;
        return this;
    }

    public Module setModuleDataTemplateJson(String moduleDataTemplateJson) {
        this.moduleDataTemplateJson = moduleDataTemplateJson;
        return this;
    }

    public Module setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Module setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

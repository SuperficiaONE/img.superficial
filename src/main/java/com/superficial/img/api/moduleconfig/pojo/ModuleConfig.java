package com.superficial.img.api.moduleconfig.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
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
public class ModuleConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long moduleConfigId;
    private Long siteId;
    private String moduleName;
    private Integer moduleOrder;
    private Long moduleId;
    private String dataUrl;
    private String createBy;
    private String updateBy;
    private Date createAt;
    private Date updateAt;

    public ModuleConfig setModuleConfigId(Long moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
        return this;
    }

    public ModuleConfig setSiteId(Long siteId) {
        this.siteId = siteId;
        return this;
    }

    public ModuleConfig setModuleId(Long moduleId) {
        this.moduleId = moduleId;
        return this;
    }



    public ModuleConfig setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
        return this;
    }

    public ModuleConfig setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public ModuleConfig setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public ModuleConfig setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public ModuleConfig setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

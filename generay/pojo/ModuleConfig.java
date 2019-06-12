package pojo;

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
public class ModuleConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long moduleConfigId;
    private Long siteId;
    private Long moduleId;
    private String dataJson;
    private String dataUrl;
    private String createBy;
    private String updateBy;
    private Date createAt;
    private Date updateAt;


    public Long getModuleConfigId() {
        return moduleConfigId;
    }

    public void setModuleConfigId(Long moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "ModuleConfig{" +
        ", moduleConfigId=" + moduleConfigId +
        ", siteId=" + siteId +
        ", moduleId=" + moduleId +
        ", dataJson=" + dataJson +
        ", dataUrl=" + dataUrl +
        ", createBy=" + createBy +
        ", updateBy=" + updateBy +
        ", createAt=" + createAt +
        ", updateAt=" + updateAt +
        "}";
    }
}

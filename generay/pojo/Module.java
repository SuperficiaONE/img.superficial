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
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long moduleId;
    private String moduleTitle;
    private String modulePreviewImg;
    private String moduleJson;
    private String moduleDataTemplateJson;
    private Date createAt;
    private Date updateAt;


    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public String getModulePreviewImg() {
        return modulePreviewImg;
    }

    public void setModulePreviewImg(String modulePreviewImg) {
        this.modulePreviewImg = modulePreviewImg;
    }

    public String getModuleJson() {
        return moduleJson;
    }

    public void setModuleJson(String moduleJson) {
        this.moduleJson = moduleJson;
    }

    public String getModuleDataTemplateJson() {
        return moduleDataTemplateJson;
    }

    public void setModuleDataTemplateJson(String moduleDataTemplateJson) {
        this.moduleDataTemplateJson = moduleDataTemplateJson;
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
        return "Module{" +
        ", moduleId=" + moduleId +
        ", moduleTitle=" + moduleTitle +
        ", modulePreviewImg=" + modulePreviewImg +
        ", moduleJson=" + moduleJson +
        ", moduleDataTemplateJson=" + moduleDataTemplateJson +
        ", createAt=" + createAt +
        ", updateAt=" + updateAt +
        "}";
    }
}

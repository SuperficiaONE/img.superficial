package pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-06-15
 */
public class ModuleConfigData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块数据配置id
     */
    private Long moduleConfigDataId;
    private Integer dataOrder;
    private Integer moduleType;
    /**
     * 模块配置id
     */
    private Long moduleConfigId;
    /**
     * 模块id
     */
    private Long moduleId;
    /**
     * 站点id
     */
    private Long siteId;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateAt;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 0.不是样本数据 1. 是样本数据
     */
    private Integer isExample;


    public Long getModuleConfigDataId() {
        return moduleConfigDataId;
    }

    public void setModuleConfigDataId(Long moduleConfigDataId) {
        this.moduleConfigDataId = moduleConfigDataId;
    }

    public Integer getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(Integer dataOrder) {
        this.dataOrder = dataOrder;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Long getModuleConfigId() {
        return moduleConfigId;
    }

    public void setModuleConfigId(Long moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getIsExample() {
        return isExample;
    }

    public void setIsExample(Integer isExample) {
        this.isExample = isExample;
    }

    @Override
    public String toString() {
        return "ModuleConfigData{" +
        ", moduleConfigDataId=" + moduleConfigDataId +
        ", dataOrder=" + dataOrder +
        ", moduleType=" + moduleType +
        ", moduleConfigId=" + moduleConfigId +
        ", moduleId=" + moduleId +
        ", siteId=" + siteId +
        ", createBy=" + createBy +
        ", updateBy=" + updateBy +
        ", updateAt=" + updateAt +
        ", createAt=" + createAt +
        ", isExample=" + isExample +
        "}";
    }
}

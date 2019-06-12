package pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-05-06
 */
public class TbPlanLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long planLogId;
    private Long planId;
    private Integer process;
    private String planLogContent;
    private Date logTime;
    private Date createAt;
    private Date updateAt;


    public Long getPlanLogId() {
        return planLogId;
    }

    public void setPlanLogId(Long planLogId) {
        this.planLogId = planLogId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getPlanLogContent() {
        return planLogContent;
    }

    public void setPlanLogContent(String planLogContent) {
        this.planLogContent = planLogContent;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
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
        return "TbPlanLog{" +
        ", planLogId=" + planLogId +
        ", planId=" + planId +
        ", process=" + process +
        ", planLogContent=" + planLogContent +
        ", logTime=" + logTime +
        ", createAt=" + createAt +
        ", updateAt=" + updateAt +
        "}";
    }
}

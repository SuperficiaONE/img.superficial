package com.superficial.img.api.plan.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxc
 * @since 2019-04-05
 */
@Data
public class TbPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 计划表的主键
     */
    @TableId
    private Long planId;
    /**
     * 创建人
     */
    private Long userId;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 计划内容
     */
    private String planContent;
    /**
     * 计划开始时间
     */
    private Date planStartTime;
    /**
     * 计划结束时间
     */
    private Date planEndTime;
    /**
     * 计划完成预计完成时间(单位天 保留两位小数四舍五入)
     */
    private BigDecimal planExpectDays;
    /**
     * 计划超时理由(超时必填)
     */
    private String planOutTimeReason;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;

    public TbPlan setPlanId(Long planId) {
        this.planId = planId;
        return this;
    }

    public TbPlan setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TbPlan setPlanName(String planName) {
        this.planName = planName;
        return this;
    }

    public TbPlan setPlanContent(String planContent) {
        this.planContent = planContent;
        return this;
    }

    public TbPlan setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
        return this;
    }

    public TbPlan setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
        return this;
    }

    public TbPlan setPlanExpectDays(BigDecimal planExpectDays) {
        this.planExpectDays = planExpectDays;
        return this;
    }

    public TbPlan setPlanOutTimeReason(String planOutTimeReason) {
        this.planOutTimeReason = planOutTimeReason;
        return this;
    }

    public TbPlan setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbPlan setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}

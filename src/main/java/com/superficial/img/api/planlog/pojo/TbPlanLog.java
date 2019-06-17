package com.superficial.img.api.planlog.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
public class TbPlanLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long planLogId;
    private Long planId;
    private Integer process;
    private String planLogContent;
    private Date logTime;
    private Date createAt;
    private Date updateAt;
    private  String updateUser;
    private String createUser;

    public TbPlanLog setPlanLogId(Long planLogId) {
        this.planLogId = planLogId;
        return this;
    }

    public TbPlanLog setPlanId(Long planId) {
        this.planId = planId;
        return this;
    }

    public TbPlanLog setProcess(Integer process) {
        this.process = process;
        return this;
    }

    public TbPlanLog setPlanLogContent(String planLogContent) {
        this.planLogContent = planLogContent;
        return this;
    }

    public TbPlanLog setLogTime(Date logTime) {
        this.logTime = logTime;
        return this;
    }

    public TbPlanLog setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public TbPlanLog setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public TbPlanLog setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public TbPlanLog setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }
}

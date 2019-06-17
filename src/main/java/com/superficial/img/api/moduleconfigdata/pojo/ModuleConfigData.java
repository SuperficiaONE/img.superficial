package com.superficial.img.api.moduleconfigdata.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Builder;
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
 * @since 2019-06-15
 */
@Setter
@Getter
@Builder
public class ModuleConfigData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块数据配置id
     */
    @TableId
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


}

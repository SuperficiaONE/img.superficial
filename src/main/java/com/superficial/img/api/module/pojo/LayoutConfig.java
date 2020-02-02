package com.superficial.img.api.module.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;
@TableName("tb_layout_config")
public class LayoutConfig {
    /**
     * 布局配置表主键
     */
    @TableId
    private Long layoutConfigId;
    /**
     * 布局表的主键
     */
    private Long layoutId;
    /**
     * 组件表主键
     */
    private Long moduleId;
    /**
     * 当前组件的样式
     */
    private String styleText;
    /**
     * 链接
     */
    private String link_text;
    /**
     * 站点
     */
    private Long siteId;
    /**
     * 脚本 加载组件的脚本
     */
    private String loadScript;

    private Date createAt;

    private Date updateAt;

    private String createBy;

    private String updateBy;
}

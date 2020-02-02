package com.superficial.img.api.module.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("tb_module")
public class Module {
    @TableId
    private Long id;
    private Long templateId;
    /**
     * 是否滚动 决定 数据加载是否是滚动的 0.否 1.是
     */
    private Integer scrollStatus;
    /**
     * 页容量
     */
    private Integer pageSize;
}

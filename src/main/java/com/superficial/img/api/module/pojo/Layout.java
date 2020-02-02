package com.superficial.img.api.module.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;
@TableName("tb_layout")
public class Layout {
    @TableId
    private Long layoutId;
    private Long templateId;
    private Date createAt;
    private Date updateAt;
    private String createBy;
    private String updateBy;

}

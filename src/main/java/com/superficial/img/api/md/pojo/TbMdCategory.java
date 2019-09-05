package com.superficial.img.api.md.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@TableName("tb_md_category")
public class TbMdCategory {
    /**
     * 文档分类表主键
     */
    @TableId
    private Long mdCategoryId;
    /**
     * 文档分类名称
     */
    private String mdCategoryName;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
}

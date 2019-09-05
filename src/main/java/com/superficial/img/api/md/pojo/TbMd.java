package com.superficial.img.api.md.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("tb_md")
public class TbMd {

    /**
     * markdown文档表主键
     */
    @TableId
    private Long mdId;
    /**
     * markdown文档主题
     */
    private String mdTitle;
    /**
     * 文档内容
     */
    private String mdContent;
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
    /**
     * 审核状态：0.不通过 1.通过
     */
    private String auditFlag;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文档分类表主键
     */
    private Long mdCategoryId;
    /**
     * 喜欢数量
     */
    private Integer likeCount;
    /**
     * 不喜欢数量
     */
    private Integer dislikeCount;
    /**
     * 收藏数量
     */
    private Integer collectCount;
}

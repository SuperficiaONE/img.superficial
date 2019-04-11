package com.superficial.img.api.tb.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableColVO {
    /**
     * 设定字段名。字段名的设定非常重要，且是表格数据列的唯一标识
     */
    private String field;
    /**
     * 设定标题名称
     */
    private String title;
    /**
     * 设定列宽，若不填写，则自动分配；若填写，则支持值为：数字、百分比
     * 请结合实际情况，对不同列做不同设定。
     */
    private Double width;
    /**
     * 局部定义当前常规单元格的最小宽度（默认：60），
     * 一般用于列宽自动分配的情况。
     * 其优先级高于基础参数中的 cellMinWidth
     */
    private Double minWidth;
    /**
     * normal（常规列，无需设定）
     * checkbox（复选框列）
     * radio（单选框列，layui 2.4.0 新增）
     * numbers（序号列）
     * space（空列）
     */
    private String type;
    /**
     * 是否允许排序（默认：false）。如果设置 true，则在对应的表头显示排序icon，从而对列开启排序功能。
     * 注意：不推荐对值同时存在“数字和普通字符”的列开启排序，因为会进入字典序比对。比如：'贤心' > '2' > '100'，
     * 这可能并不是你想要的结果，但字典序排列算法（ASCII码比对）就是如此。
     */
    private  Boolean sort;
    /**
     * 	固定列。可选值有：left（固定在左）、right（固定在右）。一旦设定，对应的列将会被固定在左或右，
     * 	不随滚动条而滚动。
     * 注意：如果是固定在左，该列必须放在表头最前面；如果是固定在右，该列必须放在表头最后面。
     */
    private String fixed;
    /**
     * 是否全选状态（默认：false）。必须复选框列开启后才有效，如果设置 true，则表示复选框默认全部选中。
     */
    private Boolean LAY_CHECKED;
    /**
     * 是否初始隐藏列，默认：false。layui 2.4.0 新增
     */
    private Boolean hide;

    /**
     * 是否开启该列的自动合计功能，默认：false。layui 2.4.0 新增
     */
    private Boolean totalRow;

    /**
     *用于显示自定义的合计文本。layui 2.4.0 新增
     */
    private String totalRowText;

    /**
     *是否禁用拖拽列宽（默认：false）。默认情况下会根据列类型（type）来决定是否禁用，
     * 如复选框列，会自动禁用。而其它普通列，默认允许拖拽列宽，当然你也可以设置 true 来禁用该功能。
     */
    private Boolean unresize;

    /**
     *单元格编辑类型（默认不开启）目前只支持：text（输入框）
     */
    private String edit;

    /**
     *自定义单元格点击事件名，以便在 tool 事件中完成对该单元格的业务处理
     */
    private String event;

    /**
     *自定义单元格样式。即传入 CSS 样式
     */
    private String style;

    /**
     *单元格排列方式。可选值有：left（默认）、center（居中）、right（居右）
     */
    private String align;

    /**
     * 单元格所占列数（默认：1）。一般用于多级表头
     *
     */
    private  Integer colspan;

    /**
     *单元格所占行数（默认：1）。一般用于多级表头
     */
    private Integer rowspan;

    /**
     *自定义列模板，模板遵循 laytpl 语法。
     * 这是一个非常实用的功能，你可借助它实现逻辑处理，
     * 以及将原始数据转化成其它格式，如时间戳转化为日期字符等
     */
    private String templet;

    /**
     *绑定列工具条。设定后，可在每行列中出现一些自定义的操作性按钮
     */
    private String toolbar;

    public TableColVO setField(String field) {
        this.field = field;
        return this;
    }

    public TableColVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public TableColVO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public TableColVO setMinWidth(Double minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public TableColVO setType(String type) {
        this.type = type;
        return this;
    }

    public TableColVO setSort(Boolean sort) {
        this.sort = sort;
        return this;
    }

    public TableColVO setFixed(String fixed) {
        this.fixed = fixed;
        return this;
    }

    public TableColVO setLAY_CHECKED(Boolean LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
        return this;
    }

    public TableColVO setHide(Boolean hide) {
        this.hide = hide;
        return this;
    }

    public TableColVO setTotalRow(Boolean totalRow) {
        this.totalRow = totalRow;
        return this;
    }

    public TableColVO setTotalRowText(String totalRowText) {
        this.totalRowText = totalRowText;
        return this;
    }

    public TableColVO setUnresize(Boolean unresize) {
        this.unresize = unresize;
        return this;
    }

    public TableColVO setEdit(String edit) {
        this.edit = edit;
        return this;
    }

    public TableColVO setEvent(String event) {
        this.event = event;
        return this;
    }

    public TableColVO setStyle(String style) {
        this.style = style;
        return this;
    }

    public TableColVO setAlign(String align) {
        this.align = align;
        return this;
    }

    public TableColVO setColspan(Integer colspan) {
        this.colspan = colspan;
        return this;
    }

    public TableColVO setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
        return this;
    }

    public TableColVO setTemplet(String templet) {
        this.templet = templet;
        return this;
    }

    public TableColVO setToolbar(String toolbar) {
        this.toolbar = toolbar;
        return this;
    }
}

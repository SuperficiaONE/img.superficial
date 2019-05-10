package com.superficial.img.common.vo;

import com.superficial.img.common.vo.TableColVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class TableVO<T,D> {
    /**
     * 指定原始 table 容器的选择器或
     * DOM，方法渲染方式必填
     */
    String elem;
    /**
     * 异步数据接口相关参数。
     * 其中 url 参数为必填项
     */
    String url;

    /**
     * String/DOM/Boolean
     * 开启表格头部工具栏区域，该参数支持四种类型值：
     * toolbar: '#toolbarDemo' //指向自定义工具栏模板选择器
     * toolbar: '<div>xxx</div>' //直接传入工具栏模板字符
     * toolbar: true //仅开启工具栏，不显示左侧模板
     * toolbar: 'default' //让工具栏左侧显示默认的内置模板
     * 注意：
     * 1. 该参数为 layui 2.4.0 开始新增。
     * 2. 若需要“列显示隐藏”、“导出”、“打印”等功能，则必须开启该参数
     */
    T toolbar;

    /**
     * 自由配置头部工具栏右侧的图标，数组支持以下可选值：
     * filter: 显示筛选图标
     * exports: 显示导出图标
     * print: 显示打印图标
     * 可根据值的顺序显示排版图标，如：
     * defaultToolbar: ['filter', 'print', 'exports']
     * 该参数为 layui 2.4.1 新增
     */
    List<String> defaultToolbar;


    /**
     * 设定容器宽度。table容器的默认宽度是跟随它的父元素铺满，
     * 你也可以设定一个固定值，当容器中的内容超出了该宽度时，
     * 会自动出现横向滚动条。
     */
    Double width;

    /**
     * 设定容器高度
     */
    Double height;

    /**
     * （layui 2.2.1 新增）
     * 全局定义所有常规单元格的最小宽度（默认：60），
     * 一般用于列宽自动分配的情况。
     * 其优先级低于表头参数中的 minWidth
     */
    Double cellMinWidth;

    /**
     * 直接赋值数据。既适用于只展示一页数据，
     * 也非常适用于对一段已知数据进行多页展示。
     */
    D data;

    /**
     * 	是否开启合计行区域。
     * 	layui 2.4.0 新增
     */
    Boolean totalRow;
    /**
     * 开启分页（默认：false） 注：
     * 从 layui 2.2.0 开始，支持传入一个对象，
     * 里面可包含 laypage 组件所有支持的参数（jump、elem除外）
     */
    Boolean page;
    /**
     * 每页显示的条数（默认：10）。
     * 值务必对应 limits 参数的选项。
     * 优先级低于 page 参数中的 limit 参数。
     */
    Integer limit;
    /**
     * 页条数的选择项，默认：
     * [10,20,30,40,50,60,70,80,90]。
     * 优先级低于 page 参数中的 limits 参数。
     */
    List<Integer> limits;
    /**
     * 是否显示加载条（默认：true）。
     * 如果设置 false，则在切换分页时，
     * 不会出现加载条。该参数只适用于
     * url 参数开启的方式
     */
    Boolean loading;
    /**
     * 定义 table 的大标题（在文件导出等地方会用到）layui 2.4.0 新增
     */
    String title;
    /**
     * 自定义文本，如空数据时的异常提示等。注：layui 2.2.5 开始新增。
     */
    String text;
    /**
     * 默认 true，即直接由 table 组件在前端自动处理排序。
     * 若为 false，则需自主排序，通常由服务端直接返回排序好的数据。
     * 注意：该参数为 layui 2.4.4 新增
     */
    Boolean autoSort;
    /**
     * 初始排序状态。用于在数据表格渲染完毕时，默认按某个字段排序。
     */
    String initSort;
    /**
     * 设定容器唯一 id。id 是对表格的数据操作方法上是必要的传递条件，
     * 它是表格容器的索引，你在下文诸多地方都将会见识它的存在。
     *
     * 值得注意的是：从 layui 2.2.0 开始，该参数也可以自动从 <table id="test"></table> 中的 id 参数中获取。
     */
    String id;
    /**
     * line （行边框风格）
     * row （列边框风格）
     * nob （无边框风格）
     */
    String skin;
    /**
     * true/false
     */
    Boolean even;
    /**
     * sm （小尺寸）
     * lg （大尺寸）
     */
    String size;


    /**
     * 设置表头。值是一个二维数组。
     * 方法渲染方式必填
     */
    List<List<TableColVO>> cols;

    public TableVO setElem(String elem) {
        this.elem = elem;
        return this;
    }

    public TableVO setUrl(String url) {
        this.url = url;
        return this;
    }

    public TableVO setToolbar(T toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public TableVO setDefaultToolbar(List<String> defaultToolbar) {
        this.defaultToolbar = defaultToolbar;
        return this;
    }

    public TableVO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public TableVO setHeight(Double height) {
        this.height = height;
        return this;
    }

    public TableVO setCellMinWidth(Double cellMinWidth) {
        this.cellMinWidth = cellMinWidth;
        return this;
    }

    public TableVO setData(D data) {
        this.data = data;
        return this;
    }

    public TableVO setTotalRow(Boolean totalRow) {
        this.totalRow = totalRow;
        return this;
    }

    public TableVO setPage(Boolean page) {
        this.page = page;
        return this;
    }

    public TableVO setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public TableVO setLimits(List<Integer> limits) {
        this.limits = limits;
        return this;
    }

    public TableVO setLoading(Boolean loading) {
        this.loading = loading;
        return this;
    }

    public TableVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public TableVO setText(String text) {
        this.text = text;
        return this;
    }

    public TableVO setAutoSort(Boolean autoSort) {
        this.autoSort = autoSort;
        return this;
    }

    public TableVO setInitSort(String initSort) {
        this.initSort = initSort;
        return this;
    }

    public TableVO setId(String id) {
        this.id = id;
        return this;
    }

    public TableVO setSkin(String skin) {
        this.skin = skin;
        return this;
    }

    public TableVO setEven(Boolean even) {
        this.even = even;
        return this;
    }

    public TableVO setSize(String size) {
        this.size = size;
        return this;
    }

    public TableVO setCols(List<List<TableColVO>> cols) {
        this.cols = cols;
        return this;
    }
}

package com.superficial.img.common.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LayPageVO {
    /**
     * 指向存放分页的容器，值可以是容器ID、DOM对象。如：
     * 1. elem: 'id' 注意：这里不能加 # 号
     * 2. elem: document.getElementById('id')
     */
    String elem;
    /**
     * 数据总数。一般通过服务端得到
     */
    Integer count;
    /**
     * 每页显示的条数。
     * laypage将会借助 count 和 limit 计算出分页数。
     */
    Integer limit;
    /**
     * 每页条数的选择项。
     * 如果 layout 参数开启了 limit，则会出现每页条数的select选择框
     */
    List<Integer> limits;
    /**
     * 起始页。一般用于刷新类型的跳页以及HASH跳页。如：
     */
    Integer curr;
    /**
     * 连续出现的页码个数
     */
    Integer groups;
    /**
     * 自定义“上一页”的内容，支持传入普通文本和HTML
     */
    String prev;
    /**
     * 自定义“下一页”的内容，同上
     */
    String next;
    /**
     * 自定义“首页”的内容，同上
     */
    String first;
    /**
     *自定义“尾页”的内容，同上
     */
    String last;
    /**
     * 自定义排版。可选值有：count（总条目输区域）、prev（上一页区域）、
     * page（分页区域）、next（下一页区域）、
     * limit（条目选项区域）、refresh（页面刷新区域。注意：layui 2.3.0 新增）
     * 、skip（快捷跳页区域）
     */
    List<String> layout;
    /**
     * 自定义主题。支持传入：颜色值，或任意普通字符。如：
     * 1. theme: '#c00'
     * 2. theme: 'xxx' //将会生成 class="layui-laypage-xxx" 的CSS类，以便自定义主题
     */
    String theme;
    /**
     * 开启location.hash，并自定义 hash 值。
     * 如果开启，在触发分页时，会自动对url追加：
     * #!hash值={curr} 利用这个，可以在页面载入时就定位到指定页
     */
    String hash;


    public LayPageVO setElem(String elem) {
        this.elem = elem;
        return this;
    }

    public LayPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

    public LayPageVO setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public LayPageVO setLimits(List<Integer> limits) {
        this.limits = limits;
        return this;
    }

    public LayPageVO setCurr(Integer curr) {
        this.curr = curr;
        return this;
    }

    public LayPageVO setGroups(Integer groups) {
        this.groups = groups;
        return this;
    }

    public LayPageVO setPrev(String prev) {
        this.prev = prev;
        return this;
    }

    public LayPageVO setNext(String next) {
        this.next = next;
        return this;
    }

    public LayPageVO setFirst(String first) {
        this.first = first;
        return this;
    }

    public LayPageVO setLast(String last) {
        this.last = last;
        return this;
    }

    public LayPageVO setLayout(List<String> layout) {
        this.layout = layout;
        return this;
    }

    public LayPageVO setTheme(String theme) {
        this.theme = theme;
        return this;
    }

    public LayPageVO setHash(String hash) {
        this.hash = hash;
        return this;
    }
}

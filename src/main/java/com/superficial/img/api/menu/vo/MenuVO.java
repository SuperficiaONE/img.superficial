package com.superficial.img.api.menu.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class MenuVO {

        private static final long serialVersionUID = 1L;

        @TableId
        private Long menuId;

        private Long parentId;

        private String menuName;

        /**
         * 1.菜单(用作展开) 2.页面  3.按钮
         * 值关系存在dict表中
         */
        private String menuLevel;
        /**
         * 用作排序
         */
        private Integer menuOrder;
        private String url;
        /**
         * 是否需要登录: 1.需要登录 2.不需要
         */
        private String menuLogin;
        /**
         * 是否是后台菜单:1.是 2.不是
         */
        private String menuBack;

        private Date createAt;

        private Date updateAt;



}

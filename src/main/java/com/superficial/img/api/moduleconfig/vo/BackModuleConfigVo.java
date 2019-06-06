package com.superficial.img.api.moduleconfig.vo;

import com.superficial.img.api.conts.Cons;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BackModuleConfigVo {
    private String moduleConfigId;
    private String dataJson;
    /**
     *
     */
    private String moduleType;
    private String siteId;
    private String moduleOrder;
    private String modulePreviewImg;

    private String moduleName;
    private String moduleTitle;

    public BackModuleConfigVo setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public BackModuleConfigVo setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
        return this;
    }

    public BackModuleConfigVo setModulePreviewImg(String modulePreviewImg) {
        this.modulePreviewImg = modulePreviewImg;
        return this;
    }
    public String getTypeName(){
        //0.顶部广告模板 1.商品模板 2.图片楼层 3.首页翅膀 4.轮播图 5.导航栏
        switch (this.getModuleType()){
            case Cons.MODULE_TYPE_0:
                return "顶部广告模板";
            case Cons.MODULE_TYPE_1:
                return "商品模板";
            case Cons.MODULE_TYPE_2:
                return "图片楼层";
            case Cons.MODULE_TYPE_3:
                return "首页翅膀";
                case Cons.MODULE_TYPE_4:
                return "轮播图";
            case Cons.MODULE_TYPE_5:
                return "导航栏";
                default:
                    return "--";
        }

    }

    public BackModuleConfigVo setModuleConfigId(String moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
        return this;
    }

    public BackModuleConfigVo setDataJson(String dataJson) {
        this.dataJson = dataJson;
        return this;
    }

    public BackModuleConfigVo setModuleType(String moduleType) {
        this.moduleType = moduleType;
        return this;
    }

    public BackModuleConfigVo setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public BackModuleConfigVo setModuleOrder(String moduleOrder) {
        this.moduleOrder = moduleOrder;
        return this;
    }
}

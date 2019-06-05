package com.superficial.img.api.moduleconfig.vo;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IndexModuleConfigVo {
    private String dataJson;
    private String dataUrl;
    private String siteId;
    private String moduleJson;
    private Integer moduleOrder;
    private Integer moduleType;
    private Long moduleConfigId;

    public IndexModuleConfigVo setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
        return this;
    }

    public IndexModuleConfigVo setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
        return this;
    }

    public IndexModuleConfigVo setModuleConfigId(Long moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
        return this;
    }

    public IndexModuleConfigVo setDataJson(String dataJson) {
        this.dataJson = dataJson;
        return this;
    }

    public IndexModuleConfigVo setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
        return this;
    }

    public IndexModuleConfigVo setSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }

    public IndexModuleConfigVo setModuleJson(String moduleJson) {
        this.moduleJson = moduleJson;
        return this;
    }
}

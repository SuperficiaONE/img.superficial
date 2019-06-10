package com.superficial.img.api.moduleconfig.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModuleConfigVo {
    private String moduleJson;
    private String moduleConfigId;
    private String dataJson;
    private String dataUrl;

    public ModuleConfigVo setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
        return this;
    }

    public ModuleConfigVo setModuleJson(String moduleJson) {
        this.moduleJson = moduleJson;
        return this;
    }

    public ModuleConfigVo setModuleConfigId(String moduleConfigId) {
        this.moduleConfigId = moduleConfigId;
        return this;
    }

    public ModuleConfigVo setDataJson(String dataJson) {
        this.dataJson = dataJson;
        return this;
    }
}

package com.superficial.img.api.arttemplate.vo;

import com.superficial.img.api.arttemplate.pojo.TbArtTemplate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtTemplateVo extends TbArtTemplate {
    private String templateTypeText;

    public ArtTemplateVo setTemplateTypeText(String templateTypeText) {
        this.templateTypeText = templateTypeText;
        return this;
    }
}

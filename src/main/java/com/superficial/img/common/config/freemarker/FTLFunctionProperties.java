package com.superficial.img.common.config.freemarker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ConfigurationProperties(prefix = "ftl")
public class FTLFunctionProperties {
    private String version;
    private String functionClassName;
}

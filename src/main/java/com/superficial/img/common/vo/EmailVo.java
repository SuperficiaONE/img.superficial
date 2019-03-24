package com.superficial.img.common.vo;

import lombok.Data;
@Data
public class EmailVo {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String from;
    private String [] to;
    private String auth;
    private Boolean enable;
    private Boolean required;
    private Integer timeout;
    private String ssl;

    public EmailVo setHost(String host) {
        this.host = host;
        return this;
    }

    public EmailVo setPort(Integer port) {
        this.port = port;
        return this;
    }

    public EmailVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public EmailVo setPassword(String password) {
        this.password = password;
        return this;
    }

    public EmailVo setFrom(String from) {
        this.from = from;
        return this;
    }

    public EmailVo setTo(String[] to) {
        this.to = to;
        return this;
    }

    public EmailVo setAuth(String auth) {
        this.auth = auth;
        return this;
    }

    public EmailVo setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public EmailVo setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public EmailVo setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    public EmailVo setSsl(String ssl) {
        this.ssl = ssl;
        return this;
    }
}

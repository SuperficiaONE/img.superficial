package com.superficial.img.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LayUIPage  {
    private  Integer count;
    private  Integer code;
    private String msg ;
    private Object data;
    public LayUIPage setCount(Integer count) {
        this.count = count;
        return this;
    }

    public LayUIPage setCode(Integer code) {
        this.code = code;
        return this;
    }

    public LayUIPage setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public LayUIPage setData(Object data) {
        this.data = data;
        return this;
    }
}

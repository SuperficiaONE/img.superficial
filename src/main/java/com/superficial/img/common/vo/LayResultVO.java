package com.superficial.img.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LayResultVO extends  ResultVO{
    private  Integer code;

    public LayResultVO setCode(Integer code) {
        this.code = code;
        return this;
    }

    public  static  LayResultVO newSuccess(String msg, Object data){
        return new LayResultVO().setData(data).setMsg(msg).setState(1).setCode(0);
    }

    public  static  LayResultVO newSuccess(String msg){
        return new LayResultVO().setData(null).setMsg(msg).setState(1).setCode(0);
    }

    public  static  LayResultVO newSuccess(Object data){
        return new LayResultVO().setData(data).setMsg("返回成功").setState(1).setCode(0);
    }

    public  static  LayResultVO newFail(String msg,Object data){
        return new LayResultVO().setData(data).setMsg(msg).setState(0).setCode(-1);
    }

    public  static  LayResultVO newFail(String msg){
        return new LayResultVO().setData(null).setMsg(msg).setState(0).setCode(-1);
    }

    public  static  LayResultVO newFail(Object data){
        return new LayResultVO().setData(data).setMsg("返回失败").setState(-1);
    }

    public  static  LayResultVO newError(String msg){
        return new LayResultVO().setData(null).setMsg(msg).setState(-1);
    }
    @Override
    public  LayResultVO setState(Integer state){
        super.setState(state);
        return this;
    }

    @Override
    public LayResultVO setMsg(String msg) {
        super.setMsg(msg);
        return this;
    }
    @Override
    public LayResultVO setData(Object data) {
        super.setData(data);
        return this;
    }
}

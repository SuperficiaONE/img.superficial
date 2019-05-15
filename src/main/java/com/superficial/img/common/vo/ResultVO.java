package com.superficial.img.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO{
    private  Integer state;
    private String msg ;
    private Object data;
    public  ResultVO(){

    }
    public  static  ResultVO newSuccess(String msg,Object data){
        return new ResultVO().setData(data).setMsg(msg).setState(1);
    }

    public  static  ResultVO newSuccess(String msg){
        return new ResultVO().setData(null).setMsg(msg).setState(1);
    }

    public  static  ResultVO newSuccess(Object data){
        return new ResultVO().setData(data).setMsg("返回成功").setState(1);
    }

    public  static  ResultVO newFail(String msg,Object data){
        return new ResultVO().setData(data).setMsg(msg).setState(0);
    }

    public  static  ResultVO newFail(String msg){
        return new ResultVO().setData(null).setMsg(msg).setState(0);
    }

    public  static  ResultVO newFail(Object data){
        return new ResultVO().setData(data).setMsg("返回失败").setState(0);
    }

    public  static  ResultVO newError(String msg){
        return new ResultVO().setData(null).setMsg(msg).setState(-1);
    }

    public  ResultVO setState(Integer state){
        this.state = state;
        return this;
    }

    public ResultVO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultVO setData(Object data) {
        this.data = data;
        return this;
    }
}

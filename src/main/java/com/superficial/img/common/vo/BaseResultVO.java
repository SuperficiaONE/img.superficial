package com.superficial.img.common.vo;

public interface BaseResultVO<T> {
    T newSuccess(String msg,Object data);
    T newSuccess(String msg);
    T newSuccess(Object data);
    T newFail(String msg,Object data);
    T newFail(String msg);
    T newFail(Object data);
    T newError(String msg);
    T setState(Integer state);
    T setMsg(String msg);
    T setData(Object data) ;
}

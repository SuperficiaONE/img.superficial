package com.superficial.img.api.plan.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlowResultVO  {
    private  Integer pages;
    private  Integer state;
    private String msg ;
    private Object data;
    public  static  FlowResultVO newSuccess(String msg,Integer pages,Object data){
      return new FlowResultVO().setMsg(msg).setPages(pages).setData(data).setState(1);
    }

    public FlowResultVO setPages(Integer pages) {
        this.pages = pages;
        return this;
    }

    public FlowResultVO setState(Integer state) {

        this.state = state;
        return this;
    }

    public FlowResultVO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public FlowResultVO setData(Object data) {
        this.data = data;
        return this;
    }
}

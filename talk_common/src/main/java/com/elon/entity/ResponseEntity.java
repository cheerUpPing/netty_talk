package com.elon.entity;

import java.io.Serializable;

/**
 * 2017/6/22 10:19.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ResponseEntity extends RequestEntity implements Serializable {

    private String result = "0000";

    private String msg = "request success";

    public ResponseEntity(){

    }

    public ResponseEntity(RequestEntity requestEntity){
        setFrom(requestEntity.getFrom());
        setTo(requestEntity.getTo());
        setCommand(requestEntity.getCommand());
        setData(requestEntity.getData());
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
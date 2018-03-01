package com.elon.entity;

import java.io.Serializable;

/**
 * 2017/6/22 10:16.
 * <p>
 * Email: cheerUpPing@163.com
 * <p>
 * 传输信息封装类
 */
public class RequestEntity implements Serializable {

    private String from;

    private String to;

    private String command;

    private Object data;

    public RequestEntity() {

    }

    public RequestEntity(String from, String to, String command, Object data) {
        this.from = from;
        this.to = to;
        this.command = command;
        this.data = data;
    }

    public RequestEntity(String from, String command, Object data) {
        this(from, null, command, data);
    }

    public RequestEntity(String from, String command) {
        this(from, null, command, null);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", command='" + command + '\'' +
                ", data=" + data +
                '}';
    }
}

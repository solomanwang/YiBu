package com.wang.result;

import java.io.Serializable;

/**
 * ClassName ResultRes
 * Author    soloman
 * Date      2019/10/15 16:56
 * Describe:
 */
public class ResultRes<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 状态
     */
    private Boolean succ;
    /**
     * 报文主题
     */
    private T body;
    /**
     * 状态描述
     */
    private String message;




    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }



    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ResultRes() {
        super();
    }

    public ResultRes(Boolean succ) {
        this.succ = succ;
    }

    public ResultRes(Boolean succ, String message) {
        this.succ = succ;
        this.message = message;
    }

    public ResultRes(Boolean succ, T body, String message) {
        this.succ = succ;
        this.body = body;
        this.message = message;
    }

}
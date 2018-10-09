package com.mlx.lingnight.http;

/**
 * ============================
 * 作    者：mlx
 * 创建日期：2018/1/3.
 * 描    述：通用返回数据
 * 修改历史：
 * ===========================
 */

public class BaseResponse<T> {
    private int code;
    private String msg;
    private T body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}

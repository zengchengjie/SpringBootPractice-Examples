package com.zcj.demo.core;

/**
 * @Auther: 10062376
 * @Date: 2018/9/5 09:43
 * @Description:
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}

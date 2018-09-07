package com.zcj.demo.core;

/**
 * @Auther: 10062376
 * @Date: 2018/9/5 09:43
 * @Description:
 */
public enum  ResultCode {
    SUCCESS(200),
    FAIL(400);
    private final  int code;
    ResultCode(int code){
        this.code = code;
    }
    public int code(){
        return code;
    }
}

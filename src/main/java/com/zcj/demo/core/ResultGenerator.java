package com.zcj.demo.core;

/**
 * @Auther: zengchengjie
 * @Date: 2018/9/5 10:27
 * @Description:
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE ="SUCCESS";

    public static Result genSuccessResult(){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }
    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}

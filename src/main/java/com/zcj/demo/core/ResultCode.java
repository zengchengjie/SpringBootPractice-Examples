package com.zcj.demo.core;

/**
 * @Auther: 10062376
 * @Date: 2018/9/5 09:43
 * @Description:
 */
public enum  ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_REGISTERED_DEVICE(403),//拒绝执行（未注册的设备发送了设备注册之外的请求）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误
    private final  int code;
    ResultCode(int code){
        this.code = code;
    }
    public int code(){
        return code;
    }
}

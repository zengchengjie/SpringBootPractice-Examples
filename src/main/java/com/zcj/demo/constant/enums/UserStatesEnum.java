package com.zcj.demo.constant.enums;

/**
 * @Auther: 10062376
 * @Date: 2018/12/17 14:04
 * @Description:
 */
public enum UserStatesEnum {
    NORMAL(0,"正常","normal"),
    FREEZE(1,"冻结","freeze");
    private Integer code;
    private String msgEn;
    private String msgZh;

    UserStatesEnum(Integer code, String msgEn, String msgZh){
        this.code = code;
        this.msgEn = msgEn;
        this.msgZh = msgZh;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msgZh;
    }

    public String getMsg(String lang) {
        if(lang == null) {
            return msgZh;
        }
        return "en".equals(lang) ? msgEn : msgZh;
    }
}

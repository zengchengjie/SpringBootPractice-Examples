package com.zcj.demo.core;

/**
 * @Auther: zengchengjie
 * @Date: 2018/9/5 10:02
 * @Description:
 */
public enum  EnumTest {
    SPRING("春天"),SUMMER("夏天");
    private final String name;
    private EnumTest (String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

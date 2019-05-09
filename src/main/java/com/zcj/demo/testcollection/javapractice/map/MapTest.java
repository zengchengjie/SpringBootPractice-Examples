package com.zcj.demo.testcollection.javapractice.map;

/**
 * @Auther: 10062376
 * @Date: 2019/5/9 10:28
 * @Description:
 */
public class MapTest {
    public static void main(String[] args) {
        MyMap<String, String> map = new MyMap<>();
        map.put("test1","张三");
        map.put("test2","李四");
        map.put("test3","王五");
        System.out.println(map.get("test3"));
        System.out.println(map.getSize());
    }
}

package com.zcj.demo.javapractice;

import com.zcj.demo.model.User;

/**
 * @Auther: 10062376
 * @Date: 2019/5/8 09:46
 * @Description:
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 == i2);
        Integer i3 = 1000;
        Integer i4 = 1000;
        System.out.println(i3 == i4);

        String s1 = "100";//在堆内存中的常量池中初始化该对象，并把引用指向他
        String s2 = "100";//string在堆内存中做了缓存，不在开辟内存，直接引用指针
        System.out.println(s1 == s2);

        String sa = "测试";
        String sb = "测试";
        System.out.println(sa == sb);
        String s3 = new String("100");
        String s4 = new String("100");
        System.out.println(s3 == s4);

        User user1 = new User();
        User user2 = new User();
        System.out.println(user1.equals(user2));

        Integer integer1 = new Integer(100);
        Long long1 = new Long(100);
        /*
        public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value == ((Integer)obj).intValue();
        }
        return false;
        }
        */
        System.out.println(integer1.equals(long1));
    }
}

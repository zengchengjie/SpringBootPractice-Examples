package com.zcj.demo.testcollection.javapractice;

import com.zcj.demo.model.User;

/**
 * @Auther: zengchengjie
 * @Date: 2019/5/8 09:46
 * @Description:
 */
public class TestInteger {
    static int a;
    int b;
    static{
        System.out.println("静态代码块1");//静态代码块只执行一次
    }
    {System.out.println("非静态代码块1");}
    static{
        System.out.println("静态代码块2");//静态代码块只执行一次
    }
    {
        System.out.println("非静态代码块2");//非静态代码块执行多次
    }

    public TestInteger() {
        System.out.println("构造方法");//构造方法执行一次
    }
    void test(){
        System.out.println("方法");
    }

    public static void main(String[] args) {
        TestInteger testInteger = new TestInteger();
        testInteger.test();
        TestInteger testInteger1 = new TestInteger();
        testInteger.test();

        //Integer类中有IntegerCache这个内部私有类,它为-128到127之间的所有整数对象提供缓存。
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1 == i2);//true
        Integer i3 = 1000;
        Integer i4 = 1000;
        System.out.println(i3 == i4);//false

        String s1 = "100";//在堆内存中的常量池中初始化该对象，并把引用指向他
        String s2 = "100";//string在堆内存中做了缓存，不在开辟内存，直接引用指针
        System.out.println(s1 == s2);//true

        String sa = "测试";
        String sb = "测试";
        System.out.println(sa == sb);//true
        //String 中的 equals 方法是被重写过的，因为 object 的 equals 方法是比较的对象的内存地址，
        //而 String 的 equals 方法比较的是对象的值。
        String s3 = new String("100");
        String s4 = new String("100");
        System.out.println(s3 == s4);//false
        System.out.println(s3.equals(s4));//true

        User user1 = new User();
        User user2 = new User();
        System.out.println(user1.equals(user2));//false

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        stringBuilder.append("b");
        stringBuilder.append("c");
        String str1 = stringBuilder.toString();
        System.out.println("StringBuilder:"+stringBuilder +"    str1:"+str1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a");
        stringBuffer.append("b");
        stringBuffer.append("c");
        String str2 = stringBuffer.toString();
        System.out.println("StringBuffer:"+stringBuffer +"    str2:"+str2);
        System.out.println(integer1.equals(long1));//false n

        Integer integer = 1;
        Long l = 1L;
        System.out.println("测试："+integer.equals(l));
    }
}

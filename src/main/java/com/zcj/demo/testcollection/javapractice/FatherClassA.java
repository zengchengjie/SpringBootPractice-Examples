package com.zcj.demo.testcollection.javapractice;

public class FatherClassA {
    protected int a;
    protected static int b;
    public static int c = 1;
    public FatherClassA() {
        System.out.println("我是父类构造方法A");
    }
    protected void testMethod(){
        System.out.println("我是父类方法1");
    }
    public static void testMethod1(){

    }
     static class B extends FatherClassA{
        static int c =0;
        int s = super.c;
        void bar(){
            super.a = 1;
            super.b = 2;
            super.testMethod();
            super.testMethod1();
            this.a = 1;
            this.b = 2;

            this.testMethod();
            this.testMethod1();
        }
        public static void testMethod1(){
        }
    }
}

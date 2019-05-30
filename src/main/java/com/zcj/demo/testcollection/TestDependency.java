package com.zcj.demo.testcollection;

/**
 * @Auther: 10062376
 * @Date: 2019/5/21 16:25
 * @Description:
 */
public class TestDependency {

    /*
    //该代码片段会造成循环依赖的问题
    public static void main(String[] args) {
        B b =new B();
    }

    static class A {
        public B b;
        public A() {
            b = new B();
        }
    }

    static class B {
        public A a;

        public B() {
            a = new A();
        }
    }*/
    public static void main(String[] args) {
//        B b = new B();
//        b.a.print();
//
//        A a = new A();
//        a.b.print();
    }

//    @Service
//    static class A {
//        @Autowired
//        public B b = new B();
//
//        public void print() {
//            System.out.println("AAA");
//        }
//        public A() {
//            b = new B();
//        }
//    }

//    @Service
//    static class B {
//        @Autowired
//        public A a = new A();
//
//        public void print() {
//            System.out.println("BBB");
//        }
//        public B() {
//            a = new A();
//        }
//    }
}

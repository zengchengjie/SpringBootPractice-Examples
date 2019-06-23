package com.zcj.demo.testcollection.javapractice.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10062376
 * @Date: 2019/5/28 11:09
 * @Description: 整理OOM出现的几种方法
 *
 */
public class OOMTest {
    public static void main(String[] args){
//        heapOOMTest();
//        methodAreaOOMTest();
//        stackOOM();
//        directMemoryOOM();
        System.out.println("max memory:     "+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.out.println("free memory:    "+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.out.println("total memory:   "+Runtime.getRuntime().totalMemory()/1024/1024+"M");
    }

    /**
     * java堆溢出：
     * 当我们不断的创建对象，而GC的时候又不能回收，当存储的对象大小超过了-Xmx的值，
     * 这时候则会出现OutOfMemoryError.
     * [-XX:+HeapDumpOnOutOfMemoryError]参数可以让jvm出现内存溢出的时候dump出内存堆转储快照。
     */
    static void heapOOMTest(){
        List<String> list = new ArrayList<>();
        while (true){
            list.add("str");
        }
    }

    /**
     * 方法区溢出
     * 在jvm的方法区中，它主要存放了类的信息，常量，静态变量等。在jdk8以前是通过“-XX:PermSize，-XX:MaxPermSize”来调整这个区域的值，
     * 但是从8开始，永久代的概念被MetaSpace（元空间）代替了，对应的参数也变成了“-XX:MetaspaceSize，-XX:MaxMetaspaceSize”。
     * 在这个例子中使用CGLib来动态生成一些类，方便实验操作。
     */
    static void methodAreaOOMTest(){
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(obj,objects);
                }
            });
            enhancer.create();
        }
    }
    static class OOMObject{}

    /**
     * 栈溢出
     * 是由线程请求的栈深度超过了jvm允许的最大范围而产生的。
     * 一般来说此类问题多出现在存在递归的地方，要从代码里重新审视递归未结束的原因，
     * 若递归的方法没问题可以根据实际情况调整“-Xss”参数的大小。还有一些代码的循坏依赖也会造成此类情况
     */
    static void stackOOM(){
        OOMTest stackOOMTest = new OOMTest();
        try {
            stackOOMTest.stackLeak();
        }catch (Throwable e){
            System.out.println("length : "+ stackLength);
            throw e;
        }
    }
    private static int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
    /**
     * 直接内存溢出
     * 本机直接内存默认与“-Xmx”设定的值一样大，可以通过“-XX:MaxDirectMemorySize”修改。
     */
    private static final int _1MB = 1024 * 1024;
    static void directMemoryOOM(){
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}

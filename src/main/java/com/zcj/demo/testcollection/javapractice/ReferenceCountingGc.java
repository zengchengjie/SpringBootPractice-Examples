package com.zcj.demo.testcollection.javapractice;

/**
 * @Auther: 10062376
 * @Date: 2019/5/20 16:34
 * @Description: 所谓对象之间的相互引用问题，如下面代码所示：除了对象
 * objA 和 objB 相互引用着对方之外，这两个对象之间再无任何引用。但是他们因为互相引用对方，
 * 导致它们的引用计数器都不为 0，于是引用计数算法无法通知 GC 回收器回收他们。
 */
public class ReferenceCountingGc {
    Object instance = null;
    private static final int _1MB = 1024 * 1024;
    //这个成员属性作用是占内存，以便能在GC日志中看清楚是否被回收过
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        testGC();
    }

    public static void testGC() {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}

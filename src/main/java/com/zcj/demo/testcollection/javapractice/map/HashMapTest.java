package com.zcj.demo.testcollection.javapractice.map;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zengchengjie
 * @Date: 2019/5/9 09:15
 * @Description: 对于hashMap的一些练习
 */
public class HashMapTest {
    public static void main(String[] args){
        Map<String,String> mapTest = new HashMap<>();//划分内存空间，构造函数初始化一个负载因子
        Integer integer= new Integer(100);
        int hashCode= integer.hashCode();
        System.out.println("hashCode值："+hashCode);
        //如何判断大量数据中是否存在某一个数据(该测试有问题)
//        hashMapTest();
//        bloomFilterTest();
//        guavaTest();
    }

    public static void hashMapTest(){
        long star = System.currentTimeMillis();

        Set<Integer> hashset = new HashSet<>(100) ;
        for (int i = 0; i < 100000; i++) {
            hashset.add(i) ;
        }
        Assert.isTrue(hashset.contains(1));
        Assert.isTrue(hashset.contains(2));
        Assert.isTrue(hashset.contains(3));

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
    public static void bloomFilterTest(){
        long star = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000) ;
        for (int i = 0; i < 100000000; i++) {
            bloomFilters.add(i + "") ;
        }
        Assert.isTrue(bloomFilters.check(1+""));
        Assert.isTrue(bloomFilters.check(2+""));
        Assert.isTrue(bloomFilters.check(3+""));
        Assert.isTrue(bloomFilters.check(999999+""));
        Assert.isTrue(bloomFilters.check(400230340+""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }

    public static void guavaTest() {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);

        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }

        Assert.isTrue(filter.mightContain(1));
        Assert.isTrue(filter.mightContain(2));
        Assert.isTrue(filter.mightContain(3));
        Assert.isTrue(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));
    }
}

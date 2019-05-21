package com.zcj.demo.testcollection.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 10062376
 * @Date: 2019/5/21 11:22
 * @Description:
 */
public class ThreadPool {
    public static void main(String[] args){
    }
    ExecutorService service = Executors.newFixedThreadPool(4);
}

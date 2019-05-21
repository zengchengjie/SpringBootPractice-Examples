package com.zcj.demo.testcollection.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 10062376
 * @Date: 2019/5/13 08:54
 * @Description:
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread("t1");

        ThreadGroup threadGroup = new ThreadGroup("testGroup");
        Thread thread2 = new Thread(threadGroup, "t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println(thread1.getThreadGroup());
        System.out.println(thread2.getThreadGroup());
        System.out.println(mainThreadGroup);
        System.out.println("-----------------------");
        ThreadTest threadTest = new ThreadTest();
        threadTest.useThread();

    }

    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 1：用同步方法实现
         * synchronized关键字也可以修饰静态方法，此时如果调用该静态方法，将会锁住整个类
         *
         * @param money
         */
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * :2：用同步代码块实现
         * 同步是一种高开销的操作，因此应该尽量减少同步的内容。
         * 通常没有必要同步整个方法，使用synchronized代码块同步关键代码即可。
         *
         * @param money
         */
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }
        //3:需要同步的变量加上volatile
        //private volatile int account = 100;

        //4:使用重入锁进项线程同步
        private Lock lock = new ReentrantLock();

        public void lockSave(int money) {
            lock.lock();
            try {
                account += money;
            } finally {
                lock.unlock();
            }
        }
        //5:使用ThreadLocal类管理共享变量account
//        private static ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
//            @Override
//            protected Integer initialValue(){
//                return 100;
//            }
//        };
//        public void save(int money){
//            account.set(account.get()+money);
//        }
//        public int getAccount(){
//            return account.get();
//        }
    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.save1(10);
                bank.lockSave(10);
                System.out.println(i + "账户余额为：" + bank.getAccount());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

}

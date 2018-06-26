package com.isoldier.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jinmeng on 2018/6/25.
 * @version 1.0
 */
public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();

    public void method() {
        lock.lock();
        System.out.println("do atomic operation");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i ++ ) {
            executorService.submit(() ->{

                reentrantLockDemo.method();

            });
        }
        executorService.shutdown();
    }
}

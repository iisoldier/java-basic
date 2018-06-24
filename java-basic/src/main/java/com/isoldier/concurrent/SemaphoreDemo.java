package com.isoldier.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 应用场景: Semaphore经常用于限制获取某种资源的线程数量
 * @author jinmeng on 2018/6/24.
 * @version 1.0
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        // 只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    // 获取许可
                    semaphore.acquire();
                    System.out.println("Accessing: " + NO);
                    Thread.sleep((long) (Math.random() * 6000));
                    // 访问完后，释放
                    semaphore.release();
                    //availablePermits()指的是当前信号灯库中有多少个可以被使用
                    System.out.println("-----------------" + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(run);
        }
        // 退出线程池
        executorService.shutdown();
    }
}

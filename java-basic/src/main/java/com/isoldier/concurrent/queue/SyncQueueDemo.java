package com.isoldier.concurrent.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jinmeng on 2018/9/29.
 * @version 1.0
 */
public class SyncQueueDemo {

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            1000, TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            kickOffEntry(i);

            Thread.sleep(200);
        }

        executor.shutdown();
    }

    private static void kickOffEntry(final int index) {
        executor.submit(() -> {
                            System.out.println("start " + index);
                            Thread.sleep(1000); // pretend to do work
                            System.out.println("stop " + index);
                            return null;
        });
    }
}

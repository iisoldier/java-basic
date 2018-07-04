package com.isoldier.concurrent.queue;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建周期性的任务
 *
 * @author jinmeng on 2018/7/3.
 * @version 1.0
 */
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        // 创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Task worker = new Task("task-" + i);

            // 周期性执行，每5秒执行一次
            scheduledThreadPool.scheduleAtFixedRate(worker, 0,3, TimeUnit.SECONDS);
        }

        Thread.sleep(10000);
        System.out.println("Shutting down executor...");
        // 关闭线程池
        scheduledThreadPool.shutdown();
        boolean isDone=false;
        // 等待线程池终止
        do {
            System.out.println("isDone:"+isDone);
            System.out.println("awaitTermination...");
            isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("isDone:"+isDone);
        } while(!isDone);
        System.out.println("Finished all threads");
    }



    static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + ", startTime = " + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ", endTime = " + LocalDateTime.now());
        }

    }


}

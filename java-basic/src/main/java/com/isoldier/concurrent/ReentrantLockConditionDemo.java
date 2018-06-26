package com.isoldier.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jinmeng on 2018/6/25.
 * @version 1.0
 */
public class ReentrantLockConditionDemo {


    private Lock theLock = new ReentrantLock();

    private Condition full = theLock.newCondition();

    private Condition empty = theLock.newCondition();
    private static List<String> cache = new LinkedList<>();

    /**生产者线程任务**/
    public void put(String str) throws InterruptedException {
        // 获取线程锁
        theLock.lock();
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(1000L);
                if (cache.size() != 0) {
                    System.out.println("超出缓存容量.暂停写入.");
                    // 生产者线程阻塞
                    full.await();
                    System.out.println("生产者线程被唤醒");
                }
                System.out.println("写入数据");
                cache.add(str);
                // 唤醒消费者
                empty.signal();
            }
        } finally {
            // 锁使用完毕后不要忘记释放
            theLock.unlock();
        }
    }
    /**消费者线程任务**/
    public void get() throws InterruptedException {
        try {
            while (!Thread.interrupted()) {
                // 获取锁
                theLock.lock();
                if (cache.size() == 0) {
                    System.out.println("缓存数据读取完毕.暂停读取");
                    // 消费者线程阻塞
                    empty.await();
                }
                System.out.println("读取数据");
                cache.remove(0);
                // 唤醒生产者线程
                full.signal();
            }
        } finally {
            // 锁使用完毕后不要忘记释放
            theLock.unlock();
        }
    }


    public static void main(String[] args){
        final ReentrantLockConditionDemo rd = new ReentrantLockConditionDemo();
        // 创建1个消费者线程
        ExecutorService consumer = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {

            consumer.submit(() -> {
                try {
                    rd.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 创建10个生产者线程
        ExecutorService producer = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            producer.submit(()-> {
                try {
                    rd.put("bread");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        consumer.shutdown();
        producer.shutdown();
    }
}

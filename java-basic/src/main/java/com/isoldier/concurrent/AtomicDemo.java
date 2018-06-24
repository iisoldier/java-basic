package com.isoldier.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jinmeng on 2018/6/24.
 * @version 1.0
 */
public class AtomicDemo {


    private AtomicLong atomicLong = new AtomicLong(0L);

    private volatile int i = 0;

    public static void main(String[] args) {
        final AtomicDemo cas = new AtomicDemo();
        List<Thread> ts = new ArrayList<Thread>(600);
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(()->{
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicLong.get());
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        for (;;) {
            long i = atomicLong.get();
            boolean suc = atomicLong.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }

}

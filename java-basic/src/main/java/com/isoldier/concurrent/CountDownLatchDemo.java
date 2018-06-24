package com.isoldier.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 1.计数为0时，无法重置
 * 2.减计数方式
 * 3.不可重复利用
 * @author jinmeng on 2018/6/24.
 * @version 1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args){
        final CountDownLatch countdown = new CountDownLatch(1);

        long startTime = System.currentTimeMillis();

        for ( int i = 0; i < 10; ++ i){
            Thread player = new Thread() {
                @Override
                public void run()    {
                    try {
                        countdown.await(); //all threads waiting
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()-startTime);
                }
            };
            player.start();
        }

        System.out.println("Go");
        countdown.countDown();
    }
}

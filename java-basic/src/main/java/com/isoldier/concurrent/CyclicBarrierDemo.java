package com.isoldier.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 用法总结
 * 1.计数达到指定值时，计数置为0重新开始
 * 2. 调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 * 3. 可重复利用
 * 4. 加计数方式
 * @author jinmeng on 2018/6/24.
 * @version 1.0
 */
public class CyclicBarrierDemo {

    public static void main(String[] args){

        new CyclicBarrierDemo().startCyclicBarrier();
    }

    public  void startCyclicBarrier() {

        // set playerCount = 5 to block
        // set playerCount = 24 to repeat 4 times
        int playerCount = 6;
        int waitPlayerCount = 6;
        CyclicBarrier barrier = new CyclicBarrier(waitPlayerCount, () -> {
            System.out.println("work completed, let us celebrating!!!");
        });

        People[] peoples = new People[playerCount];
        for (int i = 0; i < playerCount; i++) {
            peoples[i] = new People(i, barrier);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(playerCount);

        //这里如果人数小于waitPlayerCount，将会一直等待
        for (People people : peoples) {
            executorService.execute(people);
        }
        executorService.shutdown();
    }



    class People implements Runnable {
        private CyclicBarrier barrier;
        private int id;
        public People(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }
        @Override
        public void run() {
            long time = (long) (Math.random() * 2000);
            try {
                Thread.sleep(time);
                System.out.println("People " + id + " complete Task with " + time + "ms");
                barrier.await();
                System.out.println("People " + id + "after await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}

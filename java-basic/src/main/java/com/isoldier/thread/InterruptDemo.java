package com.isoldier.thread;

/**
 * 线程的中断机制测试
 * @author jinmeng on 2018/6/30.
 * @version 1.0
 */
public class InterruptDemo {


    public static void main(String[] args) throws InterruptedException {


        Thread worker = new Thread(new Worker());
        worker.start();
//        worker.interrupt();
        Thread.sleep(1000L);

//
//        System.out.println("call interrupt by another thread");
//        System.out.println("before interrupt status:"+worker.isInterrupted());
//        worker.interrupt();
//        System.out.println("after interrupt status:"+worker.isInterrupted());
//

    }


    public static class Worker implements Runnable{

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            while (!thread.isInterrupted()){
                System.out.println("I am running...");
                Thread.currentThread().interrupt();
                System.out.println("self interrupt");
            }

            System.out.println("I am over.");
        }
    }
}

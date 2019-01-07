package com.isoldier.hook;

/**
 * addShutdownHook 可以用于在程序结束时做一些特性的清理工作，下面是一个简单的使用样例
 * @author jinmeng on 2019/1/4.
 * @version 1.0
 */
public class HookDemo {

    // a class that extends thread that is to be called when program is exiting

    public static void main(String[] args) {
        try {

            // 注册shutdown hook
            Runtime.getRuntime().addShutdownHook(new Message());

            // 程序启动
            System.out.println("Program is starting...");

            // 程序运行
            System.out.println("Waiting for 3 seconds...");
            Thread.sleep(3000);

            // 程序关闭
            System.out.println("Program is closing...");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 钩子线程类 用于在程序启动时做处理
     */
    static class Message extends Thread {

        @Override
        public void run() {
            System.out.println("Bye.");
        }
    }
}
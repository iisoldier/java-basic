package com.isoldier.socket.nonblocking.client;

/**
 * @author jinmeng on 2017/11/13.
 * @version 1.0
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 9191;

        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}

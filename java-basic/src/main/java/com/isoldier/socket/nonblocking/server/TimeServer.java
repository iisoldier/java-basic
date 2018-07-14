package com.isoldier.socket.nonblocking.server;

import java.io.IOException;

/**
 * @author jinmeng on 2017/11/13.
 * @version 1.0
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 9191;
        TimeServerHandle timeServer = new TimeServerHandle(port);
        new Thread(timeServer, "NIO-TimeServerHandle").start();
    }
}

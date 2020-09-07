package com.isoldier.async;

import java.util.concurrent.CompletableFuture;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author jinmeng on 2019/3/27.
 * @version 1.0
 */
public class CompletableFutureDemo2 {


    public static void main(String[] args) throws Exception {

//        completedFutureExample();
        runAsyncExample();
    }



    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
        });

        assertFalse(cf.isDone());

    }





}

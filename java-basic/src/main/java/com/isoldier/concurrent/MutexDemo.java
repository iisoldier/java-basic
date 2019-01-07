package com.isoldier.concurrent;

/**
 *
 * 从guava 中限流源码中copy, synchronized
 * @author jinmeng on 2019/1/7.
 * @version 1.0
 */
public class MutexDemo {

    private volatile Object mutexDoNotUseDirectly;
    private Object mutex() {
        Object mutex = mutexDoNotUseDirectly;
        if (mutex == null) {
            synchronized (this) {
                mutex = mutexDoNotUseDirectly;
                if (mutex == null) {
                    mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }



    public void tryAcquire(){

        synchronized (mutex()){
            //do something that may cause thread unsafe
        }
    }
}

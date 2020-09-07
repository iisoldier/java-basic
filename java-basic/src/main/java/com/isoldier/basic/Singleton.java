package com.isoldier.basic;

/**
 * @author jinmeng on 2019/5/20.
 * @version 1.0
 */
//public class Singleton {
//
//    private Singleton(){
//
//    }
//    private static class SingletonHolder<T> {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//    public static Singleton getSingleton(){
//
//        return SingletonHolder.INSTANCE;
//    }
//}


/**
 * 双重检测
 */
public class Singleton{

    //声明成 volatile
    private volatile static Singleton instance;
    private Singleton (){}

    public static Singleton getSingleton() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
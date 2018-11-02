package com.isoldier.spi;

import java.util.ServiceLoader;

/**
 * @author jinmeng on 2018/11/2.
 * @version 1.0
 */
public class SpiDemo {

    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loaders = ServiceLoader.load(HelloInterface.class);
        for (HelloInterface in : loaders) {
            in.sayHello();
        }
    }
}

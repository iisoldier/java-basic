package com.isoldier.spi;

/**
 * @author jinmeng on 2018/11/2.
 * @version 1.0
 */
public class ImageHello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello, I am image!");
    }
}

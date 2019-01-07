package com.isoldier.guava;

import com.google.common.util.concurrent.RateLimiter;

import javax.sound.midi.Soundbank;

/**
 * @author jinmeng on 2019/1/6.
 * @version 1.0
 */
public class RateLimiterDemo {

    public static void main(String[] args){


        RateLimiter rateLimiter = RateLimiter.create(10);
        boolean permit = rateLimiter.tryAcquire();
        System.out.println(permit);




    }
}

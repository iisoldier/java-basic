package com.isoldier.guava;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author jinmeng on 2020/1/14.
 * @version 1.0
 */
public class MultiMapDemo {

    public static void main(String[] args) {
        String key = "a-key";
        Multimap<String, String> map = ArrayListMultimap.create();

        map.put(key, "firstValue");
        map.put(key, "secondValue");

        System.out.println(map);

    }
}

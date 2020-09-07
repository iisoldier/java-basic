package com.isoldier.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.List;

/**
 * @author jinmeng on 2020/1/14.
 * @version 1.0
 */
public class MapDemo {

    public static void main(String[] args) {
        Table<Integer,Integer,List<Integer>> existMap = HashBasedTable.create();

        existMap.put(1,2, Arrays.asList(4));
        existMap.put(1,3, Arrays.asList(4));
        existMap.get(1,2);
        System.out.println(existMap.get(1,3));
    }
}

package com.isoldier.guava;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author jinmeng on 2018/7/26.
 * @version 1.0
 */
public class ListsDemo {

    public static void main(String[] args) throws Exception {

        List<Long> list =  Lists.newArrayList(1L,2L,3L,4L,5L,6L,7L,8L,9L);
        List<List<Long>> originalPageList = Lists.partition(list, 3);


        System.out.println(originalPageList.toString());

    }

}

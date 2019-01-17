package com.isoldier.Trash;

import java.util.UUID;

/**
 * @author jinmeng on 2018/12/29.
 * @version 1.0
 */
public class Lab {

    public static void main(String[] args){

        System.out.println(Integer.parseInt(null));
//        System.out.println(String.valueOf(null));
        int num = -1;
        System.out.println(num >> 2);
        System.out.println(num << 2);
        System.out.println(num >>> 2);


        System.out.println(1 ^ 1);
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.getLeastSignificantBits() ^ uuid.getMostSignificantBits());
        System.out.println(Long.MAX_VALUE);



    }
}

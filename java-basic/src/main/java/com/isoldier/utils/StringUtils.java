package com.isoldier.utils;

/**
 * @author jinmeng on 2018/7/7.
 * @version 1.0
 */
public class StringUtils {

    /**
     * 对整数前面补零 返回统一的字符穿
     * @param source
     * @param length
     * @return
     */
    public static String formatStrLen(long source,int length) {

        String result = String.format("%0" + length + "d", source);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(formatStrLen( 234,9));
        System.out.println(formatStrLen(1234567890,9));

    }

}

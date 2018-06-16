package com.isoldier.consistenthash;

/**
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */
public interface UDHashFunc {

    /**
     * 根据key 自定义hash算法
     * @param key
     * @return
     */
    Long hash(Object key);
}

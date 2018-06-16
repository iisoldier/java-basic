package com.isoldier.consistenthash;

/**
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */

/**
 * FNV哈希算法全名为Fowler-Noll-Vo算法，
 * 特点和用途：FNV能快速hash大量数据并保持较小的冲突率，
 * 它的高度分散使它适用于hash一些非常相近的字符串，比如URL，hostname，文件名，text，IP地址等。
 *
 * 算法版本：FNV算法有两个版本FNV-1和FNV-1
 */
public class Fnv1HashAlg implements UDHashFunc{
    @Override
    public Long hash(Object key) {
        final int p = 16777619;
        long hash = (int) 2166136261L;
        for (int i = 0; i < key.toString().length(); i++){
            hash = (hash ^ key.toString().charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }
}

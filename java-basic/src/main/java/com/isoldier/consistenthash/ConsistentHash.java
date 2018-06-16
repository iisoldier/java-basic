package com.isoldier.consistenthash;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */
public class ConsistentHash<T> {
    /**
     * Hash计算对象，用于自定义hash算法
     */
    UDHashFunc udHashFunc;
    /**
     * 复制的节点个数
     */
    private final int numberOfReplicas;
    /**
     * 一致性Hash环
     */
    private final SortedMap<Long, T> circle = new TreeMap<>();


    /**
     * @param udHashFunc       自定义的hash算法
     * @param numberOfReplicas 复制的节点个数，增加每个节点的复制节点 防止数据倾斜
     * @param nodes            节点对象
     */
    public ConsistentHash(UDHashFunc udHashFunc, int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        this.udHashFunc = udHashFunc;
        //初始化节点
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 增加节点<br>
     * 每增加一个节点，就会在闭环上增加给定复制节点数<br>
     * 例如复制节点数是2，则每调用此方法一次，增加两个虚拟节点，这两个节点指向同一Node
     * 由于hash算法会调用node的toString方法，故按照toString去重
     *
     * @param node 节点对象
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(udHashFunc.hash(node.toString() + i), node);
        }
    }
    /**
     * 移除节点的同时移除相应的虚拟节点
     *
     * @param node 节点对象
     */
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(udHashFunc.hash(node.toString() + i));
        }
    }

    /**
     * 获得一个最近的顺时针节点
     *
     * @param key 为给定键取Hash，取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * @return 节点对象
     */
    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        long hash = udHashFunc.hash(key);
        if (!circle.containsKey(hash)) {
            //返回此映射的部分视图，其键大于等于 hash
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            //如果tailMap为空，则说明没有键值超过该hash的 则返回第一个元素
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        //每次都保证能够命中
        return circle.get(hash);
    }

}

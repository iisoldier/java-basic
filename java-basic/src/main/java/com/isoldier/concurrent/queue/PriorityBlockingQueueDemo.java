package com.isoldier.concurrent.queue;

import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * 1. 优先队列 内部的实现原理是堆实现
 *    根据比较器实现最小堆 比较器可以自己定义规则
 * @author jinmeng on 2018/7/2.
 * @version 1.0
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueueDemo demo = new PriorityBlockingQueueDemo();
        demo.testOlderFirst();
    }

    void testOlderFirst(){

        Queue<Student> defaultQueue = new PriorityBlockingQueue<>(10,Comparator.comparing(Student::getAge));
        defaultQueue.add(new Student(100));
        defaultQueue.add(new Student(20));
        defaultQueue.add(new Student(10));
        defaultQueue.add(new Student(78));
        defaultQueue.add(new Student(32));
        defaultQueue.add(new Student(48));
        while (!defaultQueue.isEmpty()) {
            System.out.println(defaultQueue.poll().getAge());
        }
    }



    class Student{
        int age;

        public Student(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }
    }


}

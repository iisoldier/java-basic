package com.isoldier.concurrent.queue;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 内部通过优先队列实现
 * @author jinmeng on 2018/7/2.
 * @version 1.0
 */
public class DelayQueueDemo {


    private static DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();


    public static void main(String[] args) {
        initConsumer();
        try {
            // 等待消费者初始化完毕
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        delayQueue.add(new DelayedElement(6000, "Task1"));
        delayQueue.add(new DelayedElement(2000, "Task2"));
        delayQueue.add(new DelayedElement(3000, "Task3"));
        delayQueue.add(new DelayedElement(4000, "Task4"));
        delayQueue.add(new DelayedElement(5000, "Task5"));
    }


    private static void initConsumer() {
        Runnable task = () -> {
            while (true) {
                try {
                    System.out.println("尝试获取延迟队列中的任务。" + LocalDateTime.now());
                    System.out.println(delayQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread consumer = new Thread(task);
        consumer.start();
    }





    static class DelayedElement implements Delayed{

        private  long delay;
        private  long expire;
        private  String msg;
        private  long now;

        public DelayedElement(long delay, String msg) {
            this.delay = delay;
            this.msg = msg;
            expire = System.currentTimeMillis() + delay;
            now = System.currentTimeMillis();
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), unit);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
        }


        @Override
        public String toString() {
            return "DelayedElement{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }
}

package com.isoldier.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 用于将大任务拆分成具体的小任务，然后归并
 * 注意点参考 https://www.liaoxuefeng.com/article/001493522711597674607c7f4f346628a76145477e2ff82000
 *
 * @author jinmeng on 2018/6/24.
 * @version 1.0
 */
public class ForkJoinDemo {


    public static void main(String[] args) throws Exception {
        // 创建随机数组成的数组:
        long[] array = new long[400];
        for(int i = 0; i < 400; i++){
            array[i] = i;
        }
        // fork/join task:
        // 最大并发数4
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }


    static class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 100;
        long[] array;
        int start;
        int end;

        SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                System.out.println(String.format("compute %d~%d = %d", start, end, sum));
                return sum;
            }
            // 任务太大,一分为二:
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask subTask1 = new SumTask(this.array, start, middle);
            SumTask subTask2 = new SumTask(this.array, middle, end);
            invokeAll(subTask1, subTask2);
            Long subResult1 = subTask1.join();
            Long subResult2 = subTask2.join();
            Long result = subResult1 + subResult2;
            System.out.println("result = " + subResult1 + " + " + subResult2 + " ==> " + result);
            return result;
        }
    }
}

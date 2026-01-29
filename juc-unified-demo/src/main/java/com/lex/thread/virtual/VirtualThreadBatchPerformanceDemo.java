package com.lex.thread.virtual;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 展示目的：測試虛擬執行緒在處理大量（1000+）Callable 任務時的執行效能與加總結果
 * @author : LEX_YU
 * @date : 19/02/2023 3:10 pm
 */
public class VirtualThreadBatchPerformanceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long time = System.currentTimeMillis();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<VirtualThreadCallableTask> tasks = new ArrayList<>();
            for (int i = 0; i < 1_000; i++) {
                tasks.add(new VirtualThreadCallableTask(i));
            }
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            long sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }
            time = System.currentTimeMillis() - time;
            System.out.println("sum = " + sum + "; time = " + time + " ms");
        }
    }
}
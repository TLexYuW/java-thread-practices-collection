package com.lex.practice.virtual;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : LEX_YU
 * @date : 19/02/2023 3:10 pm
 */
public class AppTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService executor = Executors.newFixedThreadPool(100);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            tasks.add(new Task(i));
        }
        long time = System.currentTimeMillis();
        List<Future<Integer>> futures = executor.invokeAll(tasks);
        long sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("sum = " + sum + "; time = " + time + " ms");
        executor.shutdown();
    }
}

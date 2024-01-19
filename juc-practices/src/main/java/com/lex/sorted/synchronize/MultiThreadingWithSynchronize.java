package com.lex.sorted.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author : LEX_YU
 */
public class MultiThreadingWithSynchronize {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        IntStream.range(0,1000)
                .parallel()
                .forEach(i -> {
//                    System.out.println(Thread.currentThread().getName());
                    executor.submit(MultiThreadingWithSynchronize::increment);
                });
        Thread.sleep(5_000);
        System.out.println(count);
        executor.shutdown();
    }

    static synchronized void increment() {
        count += 1;
    }
}

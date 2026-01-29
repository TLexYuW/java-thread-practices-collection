package com.lex.thread.future.api;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureHandleDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Step. 1 : 111");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return 10;
        }, threadPool).handle((i, err) -> {
            int x = 2 / 0;
            System.out.println("Step. 2 : 222");
            return i * 2;
        }).handle((i, err) -> {
            System.out.println("Step. 3 : 333");
            return i * 3;
        }).whenComplete((val, err) -> {
            System.out.println("Step. 4 : 444");
            if (err == null) {
                System.out.println("After Computed : " + val);
            }
        }).exceptionally(err -> {
            System.out.println("Step. 5");
            System.out.println(err.getMessage());
            err.printStackTrace();
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " - Main Thread");

        threadPool.shutdown();
    }
}

package com.lex.thread.future.api;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureThenApplyDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Step. 1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            return 10;
        },threadPool).thenApply(i -> {
            System.out.println("Step. 2 : " + i);
            return i * 2;
        }).thenApply(i -> {
            System.out.println("Step. 3 : " + i);
            return i * 3;
        }).whenComplete((val, err) -> {
            System.out.println("Step. 4 : " + val);
            if (err == null) {
                System.out.println("After Computed : " + val);
            }
        }).exceptionally(err -> {
            System.out.println("Step. 5");
            System.out.println(err.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " - Main Thread");

        threadPool.shutdown();
    }
}

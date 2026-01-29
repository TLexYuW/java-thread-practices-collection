package com.lex.thread.pool;

import java.util.concurrent.*;

/**
 * 展示目的：展示如何透過 Future 獲取 Callable 任務的傳回值及其異常處理機制
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class FutureCallableValueDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future future = executorService.submit(newCallable("Task 1.1"));

        System.out.println(future.isDone());

        try {
            String result = (String) future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        System.out.println(future.isDone());

        executorService.shutdown();
    }

    private static Callable newCallable(String msg) {
        return new Callable() {
            @Override
            public Object call() throws Exception {
                return Thread.currentThread().getName() + ": " + msg;
            }
        };
    }

}

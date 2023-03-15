package com.lex.practice.executorservice;

import java.util.concurrent.*;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample4 {
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

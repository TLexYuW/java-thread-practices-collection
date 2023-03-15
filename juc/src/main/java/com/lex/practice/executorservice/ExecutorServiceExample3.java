package com.lex.practice.executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future future = executorService.submit(newRunnable("Task 1.1"));

        System.out.println(future.isDone());

        try {
            future.get();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        System.out.println(future.isDone());

        executorService.shutdown();
    }

    private static Runnable newRunnable(String msg) {
        return new Runnable() {
            @Override
            public void run() {
                String completeMsg = Thread.currentThread().getName() + ": " + msg;
                System.out.println(completeMsg);
            }
        };
    }
}

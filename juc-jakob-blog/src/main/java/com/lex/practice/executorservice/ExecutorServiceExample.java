package com.lex.practice.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(newRunnable("Task 1"));
        executorService.execute(newRunnable("Task 2"));
        executorService.execute(newRunnable("Task 3"));

        executorService.shutdown();
    }

    private static Runnable newRunnable(String msg){
        return new Runnable() {
            @Override
            public void run() {
                String completeMsg = Thread.currentThread().getName() + ": " + msg;
                System.out.println(completeMsg);
            }
        };
    }
}

package com.lex.practice.executorservice;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample7 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        executorService.shutdown();

//        List<Runnable> runnables = executorService.shutdownNow();

        try {
            executorService.awaitTermination(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        executorService.shutdown();
    }
}

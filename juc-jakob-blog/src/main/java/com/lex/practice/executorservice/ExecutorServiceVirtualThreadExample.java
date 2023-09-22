package com.lex.practice.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : LEX_YU
 * @date : 19/03/2023
 */
public class ExecutorServiceVirtualThreadExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.submit(() -> {
            System.out.println("This is a Runnable that is executed by a virtual thread");
        });

        Callable<String> callable = () -> {
            System.out.println("Callable executed by virtual thread");
            return "Result from Callable";
        };

        Future<String> futureResult = executor.submit(callable);
        try {
            System.out.println(futureResult.get());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        executor.shutdown();
    }
}

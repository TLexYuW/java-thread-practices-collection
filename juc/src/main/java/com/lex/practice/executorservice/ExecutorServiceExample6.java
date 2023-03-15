package com.lex.practice.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample6 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callables = new ArrayList<>();
        callables.add(newCallable("Task 1.1"));
        callables.add(newCallable("Task 1.2"));
        callables.add(newCallable("Task 1.3"));


        try {
            List<Future<String>> results = executorService.invokeAll(callables);
            for (Future f : results) {
                System.out.println(f.get());
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

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

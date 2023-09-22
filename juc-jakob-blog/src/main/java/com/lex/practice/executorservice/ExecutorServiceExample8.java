package com.lex.practice.executorservice;

import java.util.concurrent.*;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample8 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future future = executorService.submit(newCallable("Task 1.1"));

        System.out.println(future.isDone());

        boolean mayInterrupt = true;

//        boolean wasCancelled = future.cancel(mayInterrupt);
//        System.out.println(wasCancelled);

        try {
            String result = (String) future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        } catch (CancellationException ex) {
            String msg = "Cannot call Future.get() since task was cancelled";
            System.out.println(msg);
        }

        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

//        boolean wasCancelled = future.cancel(mayInterrupt);
//        System.out.println(wasCancelled);

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

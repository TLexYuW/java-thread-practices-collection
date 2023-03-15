package com.lex.practice.executorservice;

import java.util.concurrent.*;

/**
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorServiceExample1_1 {
    public static void main(String[] args) {
        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;


        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(128)
                );
        threadPoolExecutor = Executors.newFixedThreadPool(3);

        ScheduledExecutorService scheduledExecutorService =
                new ScheduledThreadPoolExecutor(corePoolSize);


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

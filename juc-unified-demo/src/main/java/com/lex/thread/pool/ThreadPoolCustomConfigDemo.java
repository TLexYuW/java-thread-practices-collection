package com.lex.thread.pool;

import java.util.concurrent.*;

/**
 * 展示目的：展示如何手動配置 ThreadPoolExecutor 參數（核心數、最大數、排隊隊列）
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ThreadPoolCustomConfigDemo {
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

package com.lex.unsorted.virtual_thread._01;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class Demo1 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
            System.out.println(threadInfo.length + " os thread");
        }, 1, 1, TimeUnit.SECONDS);

        long l = System.currentTimeMillis();
        try(var executor = Executors.newCachedThreadPool()) {
            IntStream.range(0, 1_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }
        System.out.printf("elapsed time：%d ms", System.currentTimeMillis() - l);
    }
}

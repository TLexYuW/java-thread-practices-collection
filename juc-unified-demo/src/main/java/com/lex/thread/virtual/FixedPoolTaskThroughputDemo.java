package com.lex.thread.virtual;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 展示目的：測試固定數量平台執行緒在處理百萬級短任務時的吞吐量瓶頸
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class FixedPoolTaskThroughputDemo {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
//            ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
//            System.out.println(threadInfo.length + " os thread");
//        }, 1, 1, TimeUnit.SECONDS);

        long l = System.currentTimeMillis();
        try(var executor = Executors.newFixedThreadPool(10)) {
            IntStream.range(0, 1_000_001).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(Thread.currentThread().getName() + " working on => " + i);
                    return i;
                });
            });
        }

        System.out.printf("elapsed time: %dms\n", System.currentTimeMillis() - l);
    }
}

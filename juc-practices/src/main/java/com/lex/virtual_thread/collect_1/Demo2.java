package com.lex.virtual_thread.collect_1;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class Demo2 {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
//            ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
//            System.out.println(threadInfo.length + " os thread");
//        }, 1, 1, TimeUnit.SECONDS);

        long l = System.currentTimeMillis();
        try(var executor = Executors.newFixedThreadPool(10)) {
            IntStream.range(0, 1_000).forEach(i -> {
                executor.submit(() -> {
//                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(i);
                    return i;
                });
            });
        }

        System.out.printf("elapsed time: %dms\n", System.currentTimeMillis() - l);
    }
}

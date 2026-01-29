package com.lex.thread.virtual;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 展示目的：展示虛擬執行緒在處理百萬級併發任務時的極高吞吐量與水平擴展能力
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class VirtualThreadScalingDemo {
	public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
//            ThreadInfo[] threadInfo = threadBean.dumpAllThreads(false, false);
//            System.out.println(threadInfo.length + " os thread");
//        }, 10, 10, TimeUnit.MILLISECONDS);

		long l = System.currentTimeMillis();
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			IntStream.range(0, 1_000_001).forEach(i -> {
				executor.submit(() -> {
					Thread.sleep(Duration.ofSeconds(1));
					System.out.println("V-ThreadID = " + Thread.currentThread().threadId() + " working on => " + i);
					return i;
				});
			});
		}
		System.out.printf("elapsed time: %dms\n", System.currentTimeMillis() - l);
	}
}

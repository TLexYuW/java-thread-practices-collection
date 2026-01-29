package com.lex.thread.juc.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : LEX_YU
 */
public class SemaphoreDemo2 {
	public static void main(String[] args) {
		java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(5);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Runnable task = () -> {
			boolean permit = false;
			try {
				permit = semaphore.tryAcquire(2, TimeUnit.SECONDS);
				if (permit) {
					System.out.println("Semaphore acquired : " + Thread.currentThread().getName());
					Thread.sleep(5000);
				} else {
					System.out.println("Could not acquire semaphore");
				}
			} catch (Exception ex) {
				System.out.println("err");
			} finally {
				if (permit) semaphore.release();
			}
		};
		IntStream.range(0, 10)
				.forEach(i -> {
					executorService.submit(task);
				});
		executorService.shutdown();
	}
}

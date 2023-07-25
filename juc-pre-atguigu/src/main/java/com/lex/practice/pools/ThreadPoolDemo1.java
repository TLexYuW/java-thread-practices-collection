package com.lex.practice.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 * @date : 2023/7/25
 */
public class ThreadPoolDemo1 {
	public static void main(String[] args) {
		// One Pool 5 Threads
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		// One Pool Per Thread
//		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

		Long start = System.nanoTime();
		try {
			// 10 Tasks
			for (int i = 1; i <= 10_000_000; i++) {
				// execute
				int finalI = i;
				virtualThread(String.valueOf(finalI), ()->{
					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
				}).join();

//				fixedThreadPool.submit(() -> {
//					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
//				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fixedThreadPool.shutdown();
		}

		while (!fixedThreadPool.isTerminated()){

		}

		Long end = System.nanoTime();
		System.out.println("MILLISECONDS = " + TimeUnit.MILLISECONDS.convert((end - start), TimeUnit.NANOSECONDS));
	}

	private static Thread virtualThread(String name, Runnable runnable) {
		return Thread.ofVirtual()
				.name(name)
				.start(runnable);
	}
}

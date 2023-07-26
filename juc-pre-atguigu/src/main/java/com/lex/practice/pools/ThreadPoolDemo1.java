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
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		// One Pool expansion
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

		Long start = System.nanoTime();
		try {
			// x Tasks
			for (int i = 1; i <= 1_000_000_000; i++) {
				// execute
				int finalI = i;

//				virtualThread(String.valueOf(finalI), ()->{
//					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
//				}).join();

				// fixed
				fixedThreadPool.execute(() -> {
					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
				});

				// single
//				singleThreadExecutor.execute(()->{
//					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
//				});

				// expand
//				newCachedThreadPool.execute(() -> {
//					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
//				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fixedThreadPool.shutdown();
			singleThreadExecutor.shutdown();
			newCachedThreadPool.shutdown();
		}

		while (!fixedThreadPool.isTerminated() || !singleThreadExecutor.isTerminated()
				|| !newCachedThreadPool.isTerminated()) {

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

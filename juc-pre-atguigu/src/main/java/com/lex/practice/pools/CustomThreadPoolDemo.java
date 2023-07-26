package com.lex.practice.pools;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class CustomThreadPoolDemo {
	public static void main(String[] args) {
		BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
		ThreadPoolExecutor customThreadPoolExecutor = new ThreadPoolExecutor(2, 5,
				2L, TimeUnit.SECONDS, arrayBlockingQueue,
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		Long start = System.nanoTime();
		try {
			// x Tasks
			for (int i = 1; i <= 1_0; i++) {
				// execute
				int finalI = i;

				customThreadPoolExecutor.execute(() -> {
					System.out.println("Thread name: " + Thread.currentThread().getName() + " working => " + finalI);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			customThreadPoolExecutor.shutdown();
		}

		while (!customThreadPoolExecutor.isTerminated()) {

		}

		Long end = System.nanoTime();
		System.out.println("MILLISECONDS = " + TimeUnit.MILLISECONDS.convert((end - start), TimeUnit.NANOSECONDS));


	}
}

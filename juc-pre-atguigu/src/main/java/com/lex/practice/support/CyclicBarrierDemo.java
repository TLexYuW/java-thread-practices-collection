package com.lex.practice.support;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : Lex Yu
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class CyclicBarrierDemo {
	private static final int NUMBER = 7;

	public static void main(String[] args) {
		// CyclicBarrier
		CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
			System.out.println("-------------------Collect 7 items to call");
		});

		// collect process
		for (int i = 1; i <= 7; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName() + " item collect successfully");

				// wait
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					throw new RuntimeException(e);
				}

			},String.valueOf(i)).start();
		}
	}
}

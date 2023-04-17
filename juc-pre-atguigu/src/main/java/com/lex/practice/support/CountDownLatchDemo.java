package com.lex.practice.support;

import java.util.concurrent.CountDownLatch;

/**
 * @author : Lex Yu
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		// CountDownLatch
		CountDownLatch countDownLatch = new CountDownLatch(6);

		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " 號同學 離開教室了");

				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}

		// await until 0
		countDownLatch.await();

		System.out.println(Thread.currentThread().getName() + " 班長鎖門走人了");
	}
}

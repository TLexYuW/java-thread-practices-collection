package com.lex.thread.juc.tools;

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
				System.out.println(Thread.currentThread().getName() + " left the room.");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}

		// await until count reaches 0
		countDownLatch.await();

		System.out.println(Thread.currentThread().getName() + " main thread closing the door.");
	}
}
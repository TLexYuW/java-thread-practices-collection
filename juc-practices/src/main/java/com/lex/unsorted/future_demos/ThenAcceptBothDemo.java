package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class ThenAcceptBothDemo {
	public static void main(String[] args) {
		CompletableFuture<Integer> futrue1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(3) + 1;
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Future Task1 : " + number);
			return number;
		});

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(3) + 1;
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Future Task2 : " + number);
			return number;
		});

		futrue1.thenAcceptBoth(future2, (x, y) -> {
					System.out.printf("x = %d, y = %d, Result：%d\n", x, y, (x + y));
				}).join();

	}
}

package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class RunAfterEitherDemo {
	public static void main(String[] args) {
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			int number = new Random().nextInt(5);
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Thread = " + Thread.currentThread().getName());
			System.out.println(", Task 1 = " + number);
			return number;
		});

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			int number = new Random().nextInt(5);
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Thread = " + Thread.currentThread().getName());
			System.out.println(", Task 2 = " + number);
			return number;
		});

		future1.runAfterEither(future2, () -> {
					System.out.println("One of Tasks already done!");
					System.out.println("Thread = " + Thread.currentThread().getName());
				})
				.join();

	}
}

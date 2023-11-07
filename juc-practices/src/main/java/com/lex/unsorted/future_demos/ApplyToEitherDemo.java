package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class ApplyToEitherDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(10);
					try {
						TimeUnit.SECONDS.sleep(number);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Task 1 = " + number);
					return number;
				});
		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(10);
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task 2 = " + number);
			return number;
		});

		CompletableFuture<Integer> integerCompletableFuture = future1.applyToEither(future2, number -> {
			System.out.print("Which is Faster = " + Thread.currentThread().getName());
			System.out.println(", and Value = " + number);
			return number * 2;
		});

		Integer result = integerCompletableFuture.get();
		System.out.println(result);
	}
}

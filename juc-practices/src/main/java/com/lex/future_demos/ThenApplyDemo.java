package com.lex.future_demos;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class ThenApplyDemo {
	public static void main(String[] args) {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int result = 100;
			System.out.println("First Compute: " + result);
			return result;
		}).thenApply(number -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int result = number * 3;
			System.out.println("Second Compute: " + result);
			return result;
		});

		future.join();
	}
}

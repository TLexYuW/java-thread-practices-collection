package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class ThenComposeDemo {
	public static void main(String[] args) {
		CompletableFuture<Integer> future = CompletableFuture
				.supplyAsync(() -> {
					System.out.println("Thread = " + Thread.currentThread().getName());
					int number = new Random().nextInt(30);
					System.out.println("First Compute: " + number);
					return number;
				})
				.thenCompose(param -> CompletableFuture.supplyAsync(() -> {
					System.out.println("Thread = " + Thread.currentThread().getName());
					int number = param * 2;
					System.out.println("Second Compute: " + number);
					return number;
				}));

		future.join();
	}
}

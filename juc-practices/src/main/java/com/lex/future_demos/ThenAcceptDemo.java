package com.lex.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class ThenAcceptDemo {
	public static void main(String[] args) {
		CompletableFuture<Void> future = CompletableFuture
				.supplyAsync(() -> {
					int number = new Random().nextInt(10);
					System.out.println("First Compute: " + number);
					return number;
				}).thenAccept(number -> {
					System.out.println("Second Compute: " + number * 5);
				});

		future.join();
	}
}

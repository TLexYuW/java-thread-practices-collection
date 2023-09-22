package com.lex.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class ThenRunDemo {
	public static void main(String[] args) {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(10);
			System.out.println("First Step: " + number);
			return number;
		}).thenRun(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			System.out.println("thenRun Execution");
		});

		future.join();

	}
}

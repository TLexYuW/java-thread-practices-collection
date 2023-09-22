package com.lex.future_demos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class AllOfDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.print("future 1 Done!");
			System.out.println(", Thread = " + Thread.currentThread().getName());
			return "future 1 Done!";
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.print("future 2 Done!");
			System.out.println(", Thread = " + Thread.currentThread().getName());
			return "future 2 Done!";
		});

		CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

		combinedFuture.get();
	}
}

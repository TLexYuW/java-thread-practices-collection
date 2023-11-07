package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class AnyOfDemo {
	public static void main(String[] args)  {
		Random random = new Random();
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "hello";
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		});

		CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
		System.out.println(result.join());
	}
}

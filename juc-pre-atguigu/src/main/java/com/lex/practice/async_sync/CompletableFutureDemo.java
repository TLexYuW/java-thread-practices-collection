package com.lex.practice.async_sync;

import lombok.SneakyThrows;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Void> completableFuture1 = getVoidCompletableFuture();
		// Sync Call
		completableFuture1.get();

		CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
			System.out.println("Thread name = " + Thread.currentThread().getName() + " : completableFuture2");
			int i = 10/0;
			return 1024;
		});
		// Usually use MQ
		// Async Call
		completableFuture2.whenComplete((t, u)->{
			System.out.println(t); // t = 1024
			System.out.println(u); // u = Exception
		}).get();

	}

	@SneakyThrows
	private static CompletableFuture<Void> getVoidCompletableFuture() {
		CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
			System.out.println("Thread name = " + Thread.currentThread().getName() + " : completableFuture1");
		});
		Thread.sleep(Duration.ofSeconds(2));
		return completableFuture1;
	}
}

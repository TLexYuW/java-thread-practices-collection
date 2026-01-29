package com.lex.thread.future.api;

import java.util.concurrent.*;

/**
 * 展示目的：展示 applyToEither：兩個非同步任務競速，取最快者的結果進行後續處理
 * @author : Lex Yu
 */
public class CompletableFutureFastestWinnerDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		try {
			CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
				System.out.println("A Start");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Task 1: " + Thread.currentThread().getName());
				return "Player A";
			});

			CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
				System.out.println("B Start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Task 2: " + Thread.currentThread().getName());
				return "Player B";
			});

			CompletableFuture<String> completableFuture = playerA.applyToEither(playerB, f -> f + " is winner");


			System.out.println(Thread.currentThread().getName() + ":-:" + completableFuture.join());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}

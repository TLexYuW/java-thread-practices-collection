package com.lex.thread.future.api;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureThenCombineFlowDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

		try {
			CompletableFuture<Integer> combineResult =
					CompletableFuture.supplyAsync(() -> {
						System.out.println("Task 1: " + Thread.currentThread().getName());
						return 100;
					}).thenCombine(CompletableFuture.supplyAsync(() -> {
						System.out.println("Task 2: " + Thread.currentThread().getName());
						return 200;
					}), (x, y) -> {
						System.out.println("Task 3: " + Thread.currentThread().getName());
						return (x + y);
					}).thenCombine(CompletableFuture.supplyAsync(() -> {
						System.out.println("Task 4: " + Thread.currentThread().getName());
						return 400;
					}), (x1, y1) -> {
						System.out.println("Task 5: " + Thread.currentThread().getName());
						return x1 + y1;
					});


			System.out.println(Thread.currentThread().getName() + " :-: " + combineResult.join());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

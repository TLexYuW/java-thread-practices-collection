package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author : Lex Yu
 */
public class ThenCombineDemo {
	public static void main(String[] args) {

		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(10);
			System.out.println("Task 1 = " + number);
			return number;
		});

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			int number = new Random().nextInt(10);
			System.out.println("Task 2 = " + number);
			return number;
		});

		CompletableFuture<Integer> result = future1.thenCombine(future2, (x, y) -> x + y);

		System.out.println("Combined Result：" + result.join());
	}
}

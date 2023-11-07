package com.lex.unsorted.future_demos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Lex Yu
 */
public class RunAsyncDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Runnable runnable = () -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			System.out.println("無返回結果異步任務");
		};
		CompletableFuture.runAsync(runnable);

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			System.out.println("有返回值異步任務");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello World";
		});
		String result = future.get();
		System.out.println(result);
	}
}

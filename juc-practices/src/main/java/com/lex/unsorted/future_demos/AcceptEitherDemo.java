package com.lex.unsorted.future_demos;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class AcceptEitherDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
			int number = new Random().nextInt(10) + 1;
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Thread = " + Thread.currentThread().getName());
			System.out.println(", First Step = " + number);
			return number;
		});

		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			int number = new Random().nextInt(10) + 1;
			try {
				TimeUnit.SECONDS.sleep(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Thread = " + Thread.currentThread().getName());
			System.out.println(", Second Step = " + number);
			return number;
		});

		future1.acceptEither(future2, number -> {
			System.out.print("Which is Faster = " + Thread.currentThread().getName());
			System.out.println(", and Value = " + number);
		});

		future1.get();

	}
}

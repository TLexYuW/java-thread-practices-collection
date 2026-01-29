package com.lex.thread.future.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Lex Yu
 */
public class CompletableFutureGetDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			return "some-value";
		});
//		System.out.println(future.get());
		System.out.println(future.get(2L, TimeUnit.SECONDS));
	}
}

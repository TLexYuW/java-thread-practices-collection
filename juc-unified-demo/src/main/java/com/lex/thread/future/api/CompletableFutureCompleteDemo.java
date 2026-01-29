package com.lex.thread.future.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Lex Yu
 */
public class CompletableFutureCompleteDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			return "some-value";
		});

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		System.out.println(future.complete("??????") + "\t" + future.join());
	}
}

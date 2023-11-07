package com.lex.unsorted.future_demos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Lex Yu
 */
public class Get {
	public static void main(String[] args) {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int i =1/0;
			return 1;
		});

		try {
			f1.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("CompletableFuture Test");
	}
}

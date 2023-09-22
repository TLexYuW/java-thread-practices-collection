package com.lex.future_demos;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class Join {
	public static void main(String[] args) {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
			int i =1/0;
			return 1;
		});
		CompletableFuture.allOf(f1).join();
		System.out.println("CompletableFuture Test");
	}
}

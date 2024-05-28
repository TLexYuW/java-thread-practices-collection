package _25.thenaccept;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		/*
		CompletableFuture.supplyAsync(() -> {
			return 1;
		}).thenApply(r -> {
			return r + 2;
		}).thenApply(r -> {
			return r + 3;
		}).thenAccept(System.out::println);
		 */

		System.out.println(CompletableFuture.supplyAsync(() -> "result A")
				.thenRun(() -> {
				}).join());

		System.out.println(CompletableFuture.supplyAsync(() -> "result A")
				.thenAccept(r -> System.out.println(r + "B")).join());

		System.out.println(CompletableFuture.supplyAsync(() -> "result A")
				.thenApply(r -> {
					return r + "C";
				}).join());

	}
}

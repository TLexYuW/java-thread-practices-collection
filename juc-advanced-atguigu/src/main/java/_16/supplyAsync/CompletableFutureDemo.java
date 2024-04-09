package _16.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(6);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return "Hi supplyAsync";
		});

		while (!supplyAsync.isDone()) {
			System.out.println("Still Computing...");
			TimeUnit.SECONDS.sleep(1);
		}

		String str = supplyAsync.get();

		System.out.println(str);

	}
}

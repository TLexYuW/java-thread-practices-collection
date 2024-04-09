package _16.runAsync.fork_join_pool;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		System.out.println(runAsync.get());
	}
}

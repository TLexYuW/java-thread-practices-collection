package _16.runAsync.specified_pool;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService t_pool = Executors.newFixedThreadPool(3);

		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, t_pool);

		System.out.println(runAsync.get());

		t_pool.shutdown();
	}
}

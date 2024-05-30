package _27.applytoethier;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		try {
			CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
				System.out.println("A Start");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Task 1: " + Thread.currentThread().getName());
				return "Player A";
			});

			CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
				System.out.println("B Start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Task 2: " + Thread.currentThread().getName());
				return "Player B";
			});

			CompletableFuture<String> completableFuture = playerA.applyToEither(playerB, f -> f + " is winner");


			System.out.println(Thread.currentThread().getName() + ":-:" + completableFuture.join());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}
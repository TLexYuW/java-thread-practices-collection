package _28.thencombine.seperate;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		try {
			CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
				System.out.println("Task 1 Start : " + Thread.currentThread().getName());
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return 100;
			});

			CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
				System.out.println("Task 2 Start : " + Thread.currentThread().getName());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return 200;
			});


			CompletableFuture<Integer> totalResult = task1.thenCombine(task2, (v1, v2) -> {
				System.out.println("Task 1 and Task Both Finish : " + Thread.currentThread().getName());
				System.out.println("Value 1 : " + v1);
				System.out.println("Value 2 : " + v2);
				return v1 + v2;
			});

			System.out.println(totalResult.join());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}
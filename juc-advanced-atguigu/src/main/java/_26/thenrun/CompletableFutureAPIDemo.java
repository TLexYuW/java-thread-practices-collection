package _26.thenrun;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		try {
			CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
//						try {
//							Thread.sleep(200);
//						} catch (InterruptedException e) {
//							throw new RuntimeException(e);
//						}
						System.out.println("Task 1: " + Thread.currentThread().getName());
						return "Step. A";
					}, threadPool)
					.thenRun(() -> {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						System.out.println("Task 2: " + Thread.currentThread().getName());
					})
					.thenRun(() -> {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						System.out.println("Task 3: " + Thread.currentThread().getName());
					})
					.thenRun(() -> {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						System.out.println("Task 4: " + Thread.currentThread().getName());
					});


			System.out.println(voidCompletableFuture.get(3L, TimeUnit.SECONDS));

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}
}

// thenRun
/*
Task 1: ForkJoinPool.commonPool-worker-1
Task 2: ForkJoinPool.commonPool-worker-1
Task 3: ForkJoinPool.commonPool-worker-1
Task 4: ForkJoinPool.commonPool-worker-1
null
 */

// thenRunAsync
/*
Task 1: ForkJoinPool.commonPool-worker-1
Task 2: ForkJoinPool.commonPool-worker-1
Task 3: ForkJoinPool.commonPool-worker-1
Task 4: ForkJoinPool.commonPool-worker-2
null
 */

// thenRun + custom thread pool
/*
Task 1: pool-1-thread-1
Task 2: pool-1-thread-1
Task 3: pool-1-thread-1
Task 4: pool-1-thread-1
null
 */

// thenRunAsync + custom thread pool
/*
Task 1: pool-1-thread-1
Task 2: ForkJoinPool.commonPool-worker-1
Task 3: ForkJoinPool.commonPool-worker-1
Task 4: ForkJoinPool.commonPool-worker-1
null
 */

// compute too fast
/*
Task 1: pool-1-thread-1
Task 2: main
Task 3: main
Task 4: main
null
 */
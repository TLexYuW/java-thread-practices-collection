package _16.demo;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService t_pool = Executors.newFixedThreadPool(3);

		try {
			CompletableFuture.supplyAsync(() -> {
				System.out.println("Thread = " + Thread.currentThread().getName() + " coming ...");
				int result = ThreadLocalRandom.current().nextInt(100);
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("Computing after 5 seconds: " + result);
				if (result > 30) {
					int i = 100 / 0;
				}
				return result;
			}, t_pool).whenComplete((val, err) -> {
				if (err == null) {
					System.out.println("Computation is Done, Update Value = " + val);
				}
			}).exceptionally(err -> {
				err.printStackTrace();
				System.out.println("Exception Occur : " + err.getCause() + " - " + err.getMessage());
				return null;
			});

			System.out.println("Thread = " + Thread.currentThread().getName() + " 忙碌其他任務中 ...");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			t_pool.shutdown();
		}

//		TimeUnit.SECONDS.sleep(5);
	}

	private static void futureDemo1() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
			System.out.println("Thread = " + Thread.currentThread().getName() + " coming ...");
			int result = ThreadLocalRandom.current().nextInt(100);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("Computing after 2 seconds: " + result);
			return result;
		});

		System.out.println("Thread = " + Thread.currentThread().getName() + " 忙碌其他任務中 ...");

		System.out.println(supplyAsync.get());
	}
}

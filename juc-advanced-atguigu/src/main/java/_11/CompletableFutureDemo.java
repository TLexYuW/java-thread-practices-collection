package _11;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	@SneakyThrows
	public static void main(String[] args) {
//		demo1();

//		demo2();

		demo3();
	}

	private static void demo1() throws InterruptedException {
		long start = System.currentTimeMillis();

		TimeUnit.MILLISECONDS.sleep(1000);
		TimeUnit.MILLISECONDS.sleep(500);
		TimeUnit.MILLISECONDS.sleep(1500);

		long end = System.currentTimeMillis();
		System.out.println("Execution Time: " + ((end - start)) + " ms");
		System.out.println("Thread Name: " + Thread.currentThread().getName());
	}


	private static void demo2() throws ExecutionException, InterruptedException {
		long start = System.currentTimeMillis();

		FutureTask<String> ft1 = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task1 Over";
		});
		Thread t1 = new Thread(ft1, "t1");
		t1.start();
		System.out.println("Future Task Done = " + ft1.get());

		FutureTask<String> ft2 = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task2 Over";
		});
		Thread t2 = new Thread(ft2, "t2");
		t2.start();
		System.out.println("Future Task Done = " + ft2.get());

		FutureTask<String> ft3 = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(1500);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task3 Over";
		});
		Thread t3 = new Thread(ft3, "t3");
		t3.start();
		System.out.println("Future Task Done = " + ft3.get());

		long end = System.currentTimeMillis();
		System.out.println("Execution Time: " + ((end - start)) + " ms");
		System.out.println("Thread Name: " + Thread.currentThread().getName());
	}

	private static void demo3() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();

		ExecutorService pool = Executors.newFixedThreadPool(3);

		FutureTask<String> ft1 = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(1500);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task1 Over";
		});

		FutureTask<String> ft2 = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task2 Over";
		});

		pool.submit(ft1);
		pool.submit(ft2);

		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println("Thread Name: " + Thread.currentThread().getName());

		System.out.println("Future Task Done = " + ft1.get());
		System.out.println("Future Task Done = " + ft2.get());

		long end = System.currentTimeMillis();
		System.out.println("Execution Time: " + ((end - start)) + " ms");
		System.out.println("Thread Name: " + Thread.currentThread().getName());
		pool.shutdown();
	}
}

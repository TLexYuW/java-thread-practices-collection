package com.lex.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class WhyNotFuture {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(10);

		Future<List<Integer>> future = service.submit(() -> {
			// doing api call ...
			System.out.println("Thread = " + Thread.currentThread().getName());
//			delay(1);
//			System.out.println(10/0);
			return Arrays.asList(1, 2, 3, 4, 5);
		});



		List<Integer> integers = future.get();

		System.out.println(integers);

		service.shutdown();
	}


	private static void delay(int min){
		try {
			TimeUnit.MINUTES.sleep(min);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

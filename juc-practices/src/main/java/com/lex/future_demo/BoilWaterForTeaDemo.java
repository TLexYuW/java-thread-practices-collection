package com.lex.future_demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class BoilWaterForTeaDemo {
	public static void main(String[] args) {
		// T1
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> 洗水壺");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("洗水壺 Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> 燒開水");
			sleep(15, TimeUnit.SECONDS);
			System.out.println("燒開水 Done!");
		});

		// T2
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> 洗茶壺");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("洗茶壺 Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> 洗茶杯");
			sleep(2, TimeUnit.SECONDS);
			System.out.println("洗茶杯 Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> 拿茶葉");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("拿茶葉 Done!");
			return "Longjing";
		});

		// T3 = T1 + T2 都完成後執行
		CompletableFuture<String> f3 = f1.thenCombine(f2, (x, t) -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println(" 拿到 " + t);
			System.out.println("泡茶");
			return "上茶";
		});

		System.out.println(f3.join());

	}

	static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
		}
	}
}

package com.lex.thread.scenarios.tea;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 展示目的：使用 CompletableFuture 編排多個非同步任務（燒水、洗杯、泡茶）的依賴與合併關係
 * @author : Lex Yu
 */
public class MakeTeaCompletableFutureDemo {
	public static void main(String[] args) {
		// T1: Task to wash kettle and boil water
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> Wash kettle");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("Wash kettle Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> Boil water");
			sleep(15, TimeUnit.SECONDS);
			System.out.println("Boil water Done!");
		});

		// T2: Task to wash teapot, wash tea cups, and prepare tea leaves
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> Wash teapot");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("Wash teapot Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> Wash tea cups");
			sleep(2, TimeUnit.SECONDS);
			System.out.println("Wash tea cups Done!");

			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println("==> Prepare tea leaves");
			sleep(1, TimeUnit.SECONDS);
			System.out.println("Prepare tea leaves Done!");
			return "Longjing";
		});

		// T3 = T1 + T2: Combine tasks to make tea
		CompletableFuture<String> f3 = f1.thenCombine(f2, (unused, tea) -> {
			System.out.print("Thread : " + Thread.currentThread().getName());
			System.out.println(" Got " + tea);
			System.out.println("Making tea...");
			return "Serve tea";
		});

		System.out.println(f3.join());

	}

	static void sleep(int t, TimeUnit u) {
		try {
			u.sleep(t);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
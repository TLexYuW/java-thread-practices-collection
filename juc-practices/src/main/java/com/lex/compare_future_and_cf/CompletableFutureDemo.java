package com.lex.compare_future_and_cf;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws InterruptedException {
		UserInfoService userInfoService = new UserInfoService();
		MedalService medalService = new MedalService();
		String userId ="1";
		String uuid = "123-456-789";
		long startTime = System.currentTimeMillis();

		CompletableFuture<UserInfo> userInfoCompletableFuture =
				CompletableFuture.supplyAsync(()->{
					try {
						return userInfoService.getUserInfo(userId);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				});

		Thread.sleep(500); // Simulate Main Thread Operation Time

		CompletableFuture<MedalInfo> medalInfoCompletableFuture =
				CompletableFuture.supplyAsync(() -> {
					try {
						return medalService.getMedalInfo(uuid);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				});


		System.out.println(userInfoCompletableFuture.join());
		System.out.println(medalInfoCompletableFuture.join());

		System.out.println("Total: " + (System.currentTimeMillis() - startTime) + "ms");
	}
}

package com.lex.unsorted.compare_future_and_cf;

import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class FutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		UserInfoService userInfoService = new UserInfoService();
		MedalService medalService = new MedalService();
		String userId ="1";
		String uuid = "123-456-789";
		long startTime = System.currentTimeMillis();

		FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(() -> userInfoService.getUserInfo(userId));
		executorService.submit(userInfoFutureTask);

		Thread.sleep(500); // Simulate Main Thread Operation Time

		FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(() -> medalService.getMedalInfo(uuid));
		executorService.submit(medalInfoFutureTask);

		UserInfo userInfo = userInfoFutureTask.get();
		MedalInfo medalInfo = medalInfoFutureTask.get();
		System.out.println(userInfo + ", " + medalInfo);

		System.out.println("Total: " + (System.currentTimeMillis() - startTime) + "ms");
		executorService.shutdown();
	}
}

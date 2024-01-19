package com.lex.sorted.lock.stamped;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * @author : LEX_YU
 */
public class StampedLockDemo {
	public static void main(String[] args) {
		StampedLock lock = new StampedLock();
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.submit(() -> {
           long stamp = lock.tryOptimisticRead();
		   try {
			   System.out.println("Optimistic Lock Valid: " +  lock.validate(stamp));
			   Thread.sleep(2000);
			   System.out.println("Optimistic Lock Valid: " +  lock.validate(stamp));
			   Thread.sleep(2000);
			   System.out.println("Optimistic Lock Valid: " +  lock.validate(stamp));
		   } catch (InterruptedException e) {
			   System.out.println("err");
		   } finally {
		       lock.unlock(stamp);
		   }
		});

		executorService.submit(() -> {
			long stamp = lock.writeLock();
			try {
				System.out.println("Write Lock Acquired");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("err");
			} finally {
				System.out.println("Write Done");
				lock.unlock(stamp);
			}
		});


		executorService.shutdown();
	}
}

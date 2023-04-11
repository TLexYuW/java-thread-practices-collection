package com.lex.practice.customized_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class ThreadDemo3 {
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print5(i);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread A").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print10(i);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread B").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					shareResource.print15(i);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread C").start();
	}

}

class ShareResource {
	// 標記
	private int flag = 1; // 1 = Thread A, 2 = Thread B, 3 = Thread C

	// create Lock
	private final Lock lock = new ReentrantLock();

	// create three conditions
	private final Condition c1 = lock.newCondition();
	private final Condition c2 = lock.newCondition();
	private final Condition c3 = lock.newCondition();

	// print 5 times method
	public void print5(int loop) throws InterruptedException {
		// lock()
		lock.lock();

		try {
			// condition
			while (flag != 1) {
				// await
				c1.await();
			}
			// work
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :迴圈: " + loop);
			}
			// inform
			flag = 2; // first, revise flag = 2
			c2.signal(); // inform Thread B
		} finally {
			// unlock()
			lock.unlock();
		}
	}
	public void print10(int loop) throws InterruptedException {
		// lock()
		lock.lock();

		try {
			// condition
			while (flag != 2) {
				// await
				c2.await();
			}
			// work
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :迴圈: " + loop);
			}
			// inform
			flag = 3; // first, revise flag = 3
			c3.signal(); // inform Thread B
		} finally {
			// unlock()
			lock.unlock();
		}
	}

	public void print15(int loop) throws InterruptedException {
		// lock()
		lock.lock();

		try {
			// condition
			while (flag != 3) {
				// await
				c3.await();
			}
			// work
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + " :: " + i + " :迴圈: " + loop);
			}
			// inform
			flag = 1; // first, revise flag = 1
			c1.signal(); // inform Thread B
		} finally {
			// unlock()
			lock.unlock();
		}
	}
}

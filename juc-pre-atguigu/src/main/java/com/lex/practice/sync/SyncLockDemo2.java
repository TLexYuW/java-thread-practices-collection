package com.lex.practice.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class SyncLockDemo2 {

	public static void main(String[] args) {
		// Demonstrate Re entry
		Lock lock = new ReentrantLock(true);

		new Thread(()->{
			try {
				// lock()
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " Outside");
				try {
					// lock()
					lock.lock();
					System.out.println(Thread.currentThread().getName() + " Middle");
					try {
						// lock()
						lock.lock();
						System.out.println(Thread.currentThread().getName() + " Inside");
					} finally {
						// unlock()
//						lock.unlock();
					}
				} finally {
					// unlock()
					lock.unlock();
				}
			} finally {
				// unlock()
				lock.unlock();
			}
		},"T1").start();

		new Thread(()->{
			lock.lock();
			System.out.println("Test...");

			lock.unlock();
		},"T2").start();
	}
}

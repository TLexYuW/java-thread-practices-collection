package com.lex.practice.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class ThreadDemo2 {
	public static void main(String[] args) {
		Share share = new Share();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.increment();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread A").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.decrement();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread B").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.increment();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread C").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.decrement();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "Thread D").start();
	}
}

class Share {
	// init
	private int number = 0;

	// create Lock
	private final Lock lock = new ReentrantLock();
	private final Condition condition = lock.newCondition();


	// +1 method
	public void increment() throws InterruptedException {
		// lock()
		lock.lock();
		try {
			// condition
			while (number != 0){
				condition.await();
			}

			// number != 0, work
			number++;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			// inform
			condition.signalAll();

		} finally {
			// unlock()
			lock.unlock();
		}

	}

	// -1 method
	public void decrement() throws InterruptedException {
		// lock()
		lock.lock();
		try {
			// condition
			while (number != 1){
				condition.await();
			}
			// number != 1, work
			number--;
			System.out.println(Thread.currentThread().getName() + " :: " + number);

			// inform
			condition.signalAll();

		} finally {
			// unlock()
			lock.unlock();
		}
	}
}

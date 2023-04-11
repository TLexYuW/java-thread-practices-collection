package com.lex.practice.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class LSaleTicket {
	public static void main(String[] args) {
		LTicket lTicket = new LTicket();

		new Thread(() -> {
			// call sell ticket method
			for (int i = 0; i < 110; i++) {
				lTicket.sale();
			}
		}, "Seller A").start();

		new Thread(() -> {
			for (int i = 0; i < 110; i++) {
				lTicket.sale();
			}
		}, "Seller B").start();

		new Thread(() -> {
			for (int i = 0; i < 110; i++) {
				lTicket.sale();
			}
		}, "Seller C").start();
	}
}

class LTicket {
	private int number = 100;

	// create ReentrantLock
	private final ReentrantLock lock = new ReentrantLock();
	int k = 0;

	public void sale() {

		try {
			// lock()
			lock.lock();

			// condition: number 0 or not
			if (number > 0) {
				k++;
				System.out.println("k = " + k);
				System.out.println(Thread.currentThread().getName() + ": Sell ticket :" + (number--) + ", remaining: " + number);
			}
		} finally {
			// unlock()
			lock.unlock();
		}
	}
}

package com.lex.practice.communicate;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class ThreadDemo1 {
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
		},"Thread A").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.decrement();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		},"Thread B").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.increment();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		},"Thread C").start();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					share.decrement();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		},"Thread D").start();
	}
}

class Share {
	// init
	private int number = 0;

	// wait(), spurious wakeups, use while replace if

	// +1 method
	public synchronized void increment() throws InterruptedException {
		// condition
		while (number != 0) {
			this.wait();
		}
		// number != 0, work
		number++;
		System.out.println(Thread.currentThread().getName() + " :: " + number);
		// inform other threads
		this.notifyAll();
	}

	// -1 method
	public synchronized void decrement() throws InterruptedException {
		while (number != 1) {
			this.wait();
		}
		number--;
		System.out.println(Thread.currentThread().getName() + " :: " + number);
		this.notifyAll();
	}
}

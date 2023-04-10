package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/10
 */
public class Main {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
			while (true) {

			}
		}, "thread");
		// Set Daemon
		thread.setDaemon(true);
		thread.start();

		System.out.println(Thread.currentThread().getName() + " over");
	}
}

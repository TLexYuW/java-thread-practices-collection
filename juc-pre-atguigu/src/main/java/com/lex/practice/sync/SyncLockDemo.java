package com.lex.practice.sync;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class SyncLockDemo {
	public synchronized void add(){
		add();
	}

	public static void main(String[] args) {
//		new SyncLockDemo().add();

		// Synchronized
//		/*
		Object o = new Object();
		new Thread(() -> {
			synchronized (o) {
				System.out.println(Thread.currentThread().getName() + " Outside");
				synchronized (o) {
					System.out.println(Thread.currentThread().getName() + " Middle");
					synchronized (o) {
						System.out.println(Thread.currentThread().getName() + " Inside");
					}
				}
			}
		}, "T1").start();
//		 */
	}
}

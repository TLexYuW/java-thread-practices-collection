package com.lex.practice.sync.dead;

import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class DeadLock {

	static final Object a = new Object();
	static final Object b = new Object();
	public static void main(String[] args) {
		new Thread(()->{
			synchronized (a){
				System.out.println(Thread.currentThread().getName() + " has lock a, attempt to acquire lock b");

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				synchronized (b){
					System.out.println(Thread.currentThread().getName() + " acquire lock b");
				}
			}
		},"Thread 1").start();

		new Thread(()->{
			synchronized (b){
				System.out.println(Thread.currentThread().getName() + " has lock b, attempt to acquire lock a");

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				synchronized (a){
					System.out.println(Thread.currentThread().getName() + " acquire lock a");
				}
			}
		},"Thread 2").start();
	}
}

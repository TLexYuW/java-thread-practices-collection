package com.lex.thread.local;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author : Lex Yu
 * @date : 2023/7/25
 */
public class ThreadLocalDemo1 {
	private static final ThreadLocal<Integer> threadCounter = ThreadLocal.withInitial(() -> 0);

	public static void incrementCounter() {
		int counter = threadCounter.get();
		threadCounter.set(counter + 1);
	}

	public static int getCounterValue() {
		return threadCounter.get();
	}

	public static void clearCounter() {
		threadCounter.remove();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			incrementCounter();
			System.out.println("Thread 1 - Counter value: " + getCounterValue());
			clearCounter();
		});

		Thread thread2 = new Thread(() -> {
			incrementCounter();
			System.out.println("Thread 2 - Counter value: " + getCounterValue());
			clearCounter();
		});

		Thread thread3 = new Thread(() -> {
			incrementCounter();
			System.out.println("Thread 3 - Counter value: " + getCounterValue());
			clearCounter();
		});

		thread1.start();
		thread2.start();
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();

		System.out.println("Main thread - Counter value: " + getCounterValue());
	}
}

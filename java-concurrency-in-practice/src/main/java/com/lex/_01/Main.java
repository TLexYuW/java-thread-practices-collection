package com.lex._01;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
public class Main {
	public static void main(String[] args) {
		UnsafeSequence unsafeSequence = new UnsafeSequence();
		for (int i = 1; i <= 50; i++) {
			Thread thread = new Thread(() ->{
				System.out.println("Thread - " + Thread.currentThread().getName() + ", Next = " + unsafeSequence.getNext());
			});
			thread.start();
		}
	}
}

class UnsafeSequence {

	private int value = 1;

	/**
	 * Returns a unique value.
	 */
	public synchronized int getNext() {
		return value++;
	}
}


package com.lex._01_fund.own_test;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		AtomicSequence atomicSequence = new AtomicSequence();

		Runnable increment = () -> {
			for (int i = 1; i <= 1_000; i++) {
				atomicSequence.getNext();
			}
		};

		Thread t1 = new Thread(increment);
		Thread t2 = new Thread(increment);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("atomicSequence Next = " + atomicSequence.getValue());
	}
}

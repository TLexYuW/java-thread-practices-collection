package com.lex._01_fund.unsafe;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		UnSafeSequence unsafeSequence = new UnSafeSequence();

		Runnable increment = () -> {
			for (int i = 1; i <= 1_000; i++) {
				unsafeSequence.getNext();
			}
		};

		Thread t1 = new Thread(increment);
		Thread t2 = new Thread(increment);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("UnSafeSequence Next = " + unsafeSequence.getValue());
	}
}

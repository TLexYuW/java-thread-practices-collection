package com.lex.practice.sync;

import java.util.concurrent.Executors;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class SaleTicket {
	//create multi-thread
	public static void main(String[] args) {
		Ticket ticket = new Ticket();

		new Thread(() -> {
			// call sell ticket method
			for (int i = 0; i < 1100; i++) {
				ticket.sale();
			}
		}, "Seller A").start();

		new Thread(() -> {
			for (int i = 0; i < 1100; i++) {
				ticket.sale();
			}
		}, "Seller B").start();

		new Thread(() -> {
			for (int i = 0; i < 1100; i++) {
				ticket.sale();
			}
		}, "Seller C").start();

	}

}

class Ticket {
	// ticket amount
	private int number = 1000;
	int k = 0;

	// sell ticket, + synchronize
	public synchronized void sale() {
		// condition: ticket 0 or not
		if (number > 0) {
			k++;
			System.out.println("k = " + k);
			System.out.println(Thread.currentThread().getName() + ": Sell ticket :" + (number--) + ", remaining: " + number);
		}
	}
}

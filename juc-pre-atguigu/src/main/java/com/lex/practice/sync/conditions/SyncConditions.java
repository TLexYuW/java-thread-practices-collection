package com.lex.practice.sync.conditions;

import java.util.concurrent.TimeUnit;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/13
 */
public class SyncConditions {
	public static void main(String[] args) {

	}
}

class Phone {
	public synchronized void sendSMS() throws InterruptedException {
		// await 4 seconds
		TimeUnit.SECONDS.sleep(4);
		System.out.println("--------------------------sendSMS");
	}

	public synchronized void sendEmail() throws InterruptedException {
		System.out.println("--------------------------sendEmail");
	}

	public void getHello() throws InterruptedException {
		System.out.println("--------------------------Hello");
	}
}

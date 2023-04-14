package com.lex.practice.sync.conditions;

import java.util.concurrent.TimeUnit;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/13
 */
public class SyncConditions {
	public static void main(String[] args) throws InterruptedException {
		Phone p1 = new Phone();
		Phone p2 = new Phone();

		new Thread(()->{
			try {
				p1.sendSMS();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, "Thread A").start();

		Thread.sleep(100);

		new Thread(()->{
			try {
//				p1.sendEmail();
//				p1.getHello();
				p2.sendEmail();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, "Thread B").start();
	}
}

/**
 * 1. Stander Request, Print SMS or Email?
	 Thread name : Thread A
	 --------------------------sendSMS
	 Thread name : Thread B
	 --------------------------sendEmail
 * 2. Sleep 4 seconds in SMS method, Print SMS or Email?
	 Thread name : Thread A
	 --------------------------sendSMS
	 Thread name : Thread B
	 --------------------------sendEmail
 * 3.Create hello method without synchronized, Print SMS or Email?
	 Thread name : Thread A
	 Thread name : Thread B
	 --------------------------Hello
	 --------------------------sendSMS
 * 4.Now, we have two phones, Print SMS or Email?
	 Thread name : Thread A
	 Thread name : Thread B
	 --------------------------sendEmail
	 --------------------------sendSMS
 * 5.Two static synchronized method, 1 phone, Print SMS or Email?
	 Thread name : Thread A
	 --------------------------sendSMS
	 Thread name : Thread B
	 --------------------------sendEmail
 * 6.Two static synchronized method, 2 phone, Print SMS or Email?
	 Thread name : Thread A
	 --------------------------sendSMS
	 Thread name : Thread B
	 --------------------------sendEmail
 * 7.One static synchronized method, one synchronized method, 1 phone, Print SMS or Email?
	 Thread name : Thread A
	 Thread name : Thread B
	 --------------------------sendEmail
	 --------------------------sendSMS
 * 8.One static synchronized method, one synchronized method, 2 phone, Print SMS or Email?
	 Thread name : Thread A
	 Thread name : Thread B
	 --------------------------sendEmail
	 --------------------------sendSMS
 */
class Phone {
//	public synchronized void sendSMS() throws InterruptedException {
//		System.out.println("Thread name : " + Thread.currentThread().getName());
//		// await 4 seconds
//		TimeUnit.SECONDS.sleep(4);
//		System.out.println("--------------------------sendSMS");
//	}

	public synchronized void sendEmail() throws InterruptedException {
		System.out.println("This = " + this);
		System.out.println("Thread name : " + Thread.currentThread().getName());
		System.out.println("--------------------------sendEmail");
	}

	public void getHello() throws InterruptedException {
		System.out.println("Thread name : " + Thread.currentThread().getName());
		System.out.println("--------------------------Hello");
	}

	public static synchronized void sendSMS() throws InterruptedException {
		System.out.println("Thread name : " + Thread.currentThread().getName());
		// await 4 seconds
		TimeUnit.SECONDS.sleep(4);
		System.out.println("--------------------------sendSMS");
	}

//	public static synchronized void sendEmail() throws InterruptedException {
//		System.out.println("Thread name : " + Thread.currentThread().getName());
//		System.out.println("--------------------------sendEmail");
//	}
}

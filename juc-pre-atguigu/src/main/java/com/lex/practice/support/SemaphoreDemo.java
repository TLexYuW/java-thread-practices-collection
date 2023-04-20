package com.lex.practice.support;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 * @version : 0.0.1
 * @date : 2023/4/20
 */
// 6 cars, 3 parking space
public class SemaphoreDemo {
	public static void main(String[] args) {

		// create semaphore, number of permits
		Semaphore semaphore = new Semaphore(3);

		// 6 cars
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					// seize
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " acquire parking space");

					// set random parking time
					TimeUnit.SECONDS.sleep(new Random().nextInt(5));


					System.out.println(Thread.currentThread().getName() + " ------ leave parking space");

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// release parking space
					semaphore.release();
				}
			}, "No. " + i + " Car").start();

		}

	}
}

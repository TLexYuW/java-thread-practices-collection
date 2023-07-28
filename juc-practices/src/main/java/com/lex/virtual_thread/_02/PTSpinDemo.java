package com.lex.virtual_thread._02;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class PTSpinDemo {
	public static void main(String[] args) throws InterruptedException {
		var start = System.nanoTime();
		var totalThread = 1_000_000;
		var threads = IntStream.range(0, totalThread)
				.mapToObj(threadCount -> Thread.ofPlatform().unstarted(() -> {
					System.out.println("Platform Thread: " + Thread.currentThread());
				})).toList();
		threads.forEach(Thread::start);
		for (Thread th : threads) {
			th.join();
		}
		var end = System.nanoTime();
		System.out.println("Time used to lauch " + totalThread + " P-Threads: "
				+ TimeUnit.NANOSECONDS.toMillis(end - start) + "ms");
	}

}


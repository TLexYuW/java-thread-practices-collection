package com.lex._03_sharing_objects._3_1;

/**
 * @author : Lex Yu
 * @date : 2023/9/7
 */
public class NoVisibility {
	protected static boolean ready;
	protected static int number;

	protected static class ReaderThread extends Thread {
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}
}

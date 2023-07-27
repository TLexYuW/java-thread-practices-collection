package com.lex.virtual_thread._03;

import java.time.Duration;

/**
 * @author : Lex Yu
 * @date : 2023/7/27
 */
public class VirtualThreadDemo {
	public static void main(String[] args) {
		for (int i = 0; i < 1_000_000; i++) {
			if (i % 10_000 == 0) {
				System.out.println(i);
			}
			Thread.startVirtualThread(() -> {
				try {
					Thread.sleep(Duration.ofMinutes(10).toMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
}

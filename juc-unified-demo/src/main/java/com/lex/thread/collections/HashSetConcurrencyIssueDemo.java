package com.lex.thread.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/13
 */
public class HashSetConcurrencyIssueDemo {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();

		for (int i = 0; i < 1000; i++) {
			new Thread(() -> {
				// add element to collection
				set.add(UUID.randomUUID().toString().substring(0, 8));
				// get element from collection
				System.out.println(set);
			},String.valueOf(i)).start();
		}
	}
}

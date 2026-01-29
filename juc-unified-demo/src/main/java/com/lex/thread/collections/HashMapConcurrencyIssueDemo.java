package com.lex.thread.collections;

import java.util.*;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/13
 */
public class HashMapConcurrencyIssueDemo {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();

		for (int i = 0; i < 1000; i++) {
			String key = String.valueOf(i);
			new Thread(() -> {
				// add element to collection
				map.put(key, UUID.randomUUID().toString().substring(0, 8));
				// get element from collection
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}
}

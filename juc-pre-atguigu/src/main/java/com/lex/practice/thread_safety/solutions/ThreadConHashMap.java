package com.lex.practice.thread_safety.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : LEX_YU
 * @version : 0.0.1
 * @date : 2023/4/11
 */
public class ThreadConHashMap {
	public static void main(String[] args) {
		Map<String, String> map = new ConcurrentHashMap<>();

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

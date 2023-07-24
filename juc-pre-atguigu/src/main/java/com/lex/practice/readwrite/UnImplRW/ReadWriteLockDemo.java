package com.lex.practice.readwrite.UnImplRW;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 * @date : 2023/7/24
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();

		// create threads
		for (int i = 1; i <= 5; i++) {
			final int num = i;
			new Thread(() -> {
				myCache.put(num + "", num + "");
			},String.valueOf(i)).start();
		}

		// create threads
		for (int i = 1; i <= 5; i++) {
			final int num = i;
			new Thread(() -> {
				myCache.get(num + "");
			},String.valueOf(i)).start();
		}

	}

}

// resource class
class MyCache {
	private Map<String, Object> map = new HashMap<>();

	// put data
	public void put(String key, Object value) {
		System.out.println("Thread Name:" + Thread.currentThread().getName() + ", writing process, key=" + key);

		// sleep a while
		try {
			TimeUnit.MICROSECONDS.sleep(500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		map.put(key, value);
		System.out.println("Thread Name:" + Thread.currentThread().getName() + ", writing finished, key=" + key);
	}

	// get data
	public Object get(String key) {
		Object result = null;

		System.out.println("Thread Name:" + Thread.currentThread().getName() + ", reading process, key=" + key);

		// sleep a while
		try {
			TimeUnit.MICROSECONDS.sleep(500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		result = map.get(key);

		System.out.println("Thread Name:" + Thread.currentThread().getName() + ", reading finished, key=" + key);

		return result;
	}
}

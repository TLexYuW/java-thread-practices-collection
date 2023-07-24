package com.lex.practice.readwrite.ImplRW;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : Lex Yu
 * @date : 2023/7/24
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) throws InterruptedException {
		MyCache myCache = new MyCache();

		// create threads
		for (int i = 1; i <= 5; i++) {
			final int num = i;
			new Thread(() -> {
				myCache.put(num + "", num + "");
			}, String.valueOf(i)).start();
		}

		TimeUnit.MILLISECONDS.sleep(1000);

		// create threads
		for (int i = 1; i <= 5; i++) {
			final int num = i;
			new Thread(() -> {
				myCache.get(num + "");
			}, String.valueOf(i)).start();
		}
	}
}

// resource class
class MyCache {
	private Map<String, Object> map = new HashMap<>();

	// create ReadWriteLock Object
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	// put data
	public void put(String key, Object value) {
		// add rw Lock
		rwLock.writeLock().lock();
		// sleep a while
		try {
			System.out.println("Thread Name:" + Thread.currentThread().getName() + ", writing process, key=" + key);

			TimeUnit.MICROSECONDS.sleep(500);

			map.put(key, value);
			System.out.println("Thread Name:" + Thread.currentThread().getName() + ", writing finished, key=" + key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// release rw Lock
			rwLock.writeLock().unlock();
		}

	}

	// get data
	public Object get(String key) {
		// create read lock
		rwLock.readLock().lock();

		Object result = null;

		// sleep a while
		try {
			System.out.println("Thread Name:" + Thread.currentThread().getName() + ", reading process, key=" + key);

			TimeUnit.MICROSECONDS.sleep(500);

			result = map.get(key);
			System.out.println("Thread Name:" + Thread.currentThread().getName() + ", reading finished, key=" + key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			rwLock.readLock().unlock();
		}

		return result;
	}
}

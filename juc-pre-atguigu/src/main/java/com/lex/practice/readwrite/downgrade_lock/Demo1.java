package com.lex.practice.readwrite.downgrade_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : Lex Yu
 * @date : 2023/7/24
 */
// Downgrade ReadWriteLock
public class Demo1 {
	public static void main(String[] args) {
		ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock(); // 讀鎖
		ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock(); // 寫鎖

		// 鎖降級
		// step.1 獲取寫鎖
		writeLock.lock();
		System.out.println("abcdefg");

		// step.2 獲取讀鎖
		readLock.lock();
		System.out.println("------------------read");

		// step.3 釋放寫鎖
		writeLock.unlock();

		// step.4 釋放讀鎖
		readLock.unlock();

	}
}

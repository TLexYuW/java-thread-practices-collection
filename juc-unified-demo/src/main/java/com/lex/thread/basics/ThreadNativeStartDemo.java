package com.lex.thread.basics;

/**
 * 展示目的：展示執行緒底層 start0() 的調用機制與基本的 synchronized 鎖同步
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class ThreadNativeStartDemo {
	public static void main(String[] args) {
		// private native void start0(), JVM_StartThread
		// Thread.java, Thread.c
		// Java ??C++ Language
		Thread t1 = new Thread(() -> System.out.println("This is t1"), "t1");
		t1.start();

		Object o = new Object();
		new Thread(()->{
			synchronized (o){
				System.out.println("t2 is running");
			}
		},"t2").start();
	}
}

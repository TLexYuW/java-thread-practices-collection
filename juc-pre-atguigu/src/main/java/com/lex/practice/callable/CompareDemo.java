package com.lex.practice.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : Lex Yu
 * @version : 0.0.1
 * @date : 2023/4/17
 */
public class CompareDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// Runnable
		new Thread(new MyThread_1(), "Thread 1").start();

		// Callable
//		new Thread(new MyThread_2(), "Thread 1").start(); // Error, Thread constructor no callable

		// FutureTask
		FutureTask<Object> futureTask_1 = new FutureTask<>(new MyThread_2());

		// Lambda
		FutureTask<Integer> futureTask_2 = new FutureTask<>(()-> {
			System.out.println(Thread.currentThread().getName() + " come in callable");
			return 1024;
		});

		new Thread(futureTask_1, "Thread K").start();
		new Thread(futureTask_2, "Thread L").start();

//		while (!futureTask_2.isDone()){
//			System.out.println("Wait...");
//		}

		// FutureTask get()
		System.out.println("futureTask_2 Result: " + futureTask_2.get());
		System.out.println("futureTask_1 Result: " + futureTask_1.get());

		System.out.println(Thread.currentThread().getName() + " is over");
	}
}

class MyThread_1 implements Runnable{
	@Override
	public void run() {
	}
}

class MyThread_2 implements Callable<Object> {
	@Override
	public Object call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " come in callable");

		return 200;
	}
}
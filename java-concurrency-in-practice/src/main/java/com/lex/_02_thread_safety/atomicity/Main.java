package com.lex._02_thread_safety.atomicity;

/**
 * @author : Lex Yu
 * @date : 2023/9/6
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		LazyInitRace lazyInitRace = new LazyInitRace();

		Runnable lazyInit = () -> {
			ExpensiveObject instance = lazyInitRace.getInstance();
			System.out.println(instance.hashCode());
		};


		Thread t1 = new Thread(lazyInit);
		Thread t2 = new Thread(lazyInit);


		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}

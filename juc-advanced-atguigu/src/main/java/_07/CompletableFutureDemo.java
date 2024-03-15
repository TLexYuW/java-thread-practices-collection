package _07;

import lombok.SneakyThrows;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws Exception {
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread, "t1");
		t1.start();

		FutureTask<String> futureTask2 = new FutureTask<>(new MyThread2());
		Thread t2 = new Thread(futureTask2, "t2");
		t2.start();
		System.out.println("取得 Callable 返回值: " + futureTask2.get());

		Callable<String> callable = () -> "Task completed";
		MyThread3 myThread3 = new MyThread3(callable);
		Thread t3 = new Thread(myThread3, "t3");
		t3.start();
	}
}

class MyThread implements Runnable {
	@Override
	public void run() {
		System.out.println("Thread implements Runnable - run()");
	}
}

class MyThread2 implements Callable<String> {
	@SneakyThrows
	@Override
	public String call() throws Exception {
		System.out.println("Thread implements Callable<V> - call()");
		return "String Value";
	}
}

class MyThread3 extends FutureTask<String> {
	public MyThread3(Callable<String> callable) throws Exception {
		super(callable);
		System.out.println(callable.call());
	}
}
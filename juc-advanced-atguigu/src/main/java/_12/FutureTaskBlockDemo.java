package _12;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 */
public class FutureTaskBlockDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<String> ft1 = new FutureTask<>(() -> {
				TimeUnit.SECONDS.sleep(7);
			System.out.println("Thread Name: " + Thread.currentThread().getName());
			return "Task1 Over";
		});
		Thread t1 = new Thread(ft1, "t1");
		t1.start();
		System.out.println("Future Task Done = " + ft1.get()); // Block

		System.out.println(Thread.currentThread().getName()  + ": Compute other tasks");

//		System.out.println("Future Task Done = " + ft1.get());
	}
}

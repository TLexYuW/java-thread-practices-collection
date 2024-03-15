package _13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : Lex Yu
 */
public class FutureTaskPollingDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		FutureTask<String> ft1 = new FutureTask<>(() -> {
			System.out.println("Thread Name: " + Thread.currentThread().getName() + " Start");

			TimeUnit.SECONDS.sleep(7);

			return "Task1 Over";
		});
		Thread t1 = new Thread(ft1, "t1");
		t1.start();
		System.out.println(Thread.currentThread().getName() + ": Compute other tasks");

		while (true){
			if (ft1.isDone()) {
				System.out.println("Future Task Done = " + ft1.get());
				break;
			}else {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.println("Task Processing...");
			}
		}
	}
}

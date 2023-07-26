package _07;

import lombok.SneakyThrows;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<>(new MyThread2());
		Thread t1 = new Thread(futureTask, "t1");
		t1.start();

//		System.out.println(futureTask.get());
	}
}

class MyThread implements Runnable {
	@Override
	public void run() {

	}
}

class MyThread2 implements Callable<String> {
	@SneakyThrows
	@Override
	public String call() throws Exception {
		System.out.println("Callable - call()");
		return "Hello Callable";
	}
}


class MyThread3 implements RunnableFuture {
	@Override
	public void run() {

	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public Object get() throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}
}

class MyThread4 extends FutureTask{
	public MyThread4(Callable callable) {
		super(callable);
	}
}
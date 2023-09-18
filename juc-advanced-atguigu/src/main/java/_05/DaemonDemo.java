package _05;

import java.time.Duration;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class DaemonDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " working, " +
					(Thread.currentThread().isDaemon() ? "Daemon Thread" : "User Thread"));
			while (true){}
		}, "t1");

		t1.setDaemon(true);
		t1.start();

		Thread.sleep(Duration.ofSeconds(3));

		System.out.println(Thread.currentThread().getName( ) + " ------- main thread, " +
				(Thread.currentThread().isDaemon() ? "Daemon Thread" : "User Thread"));
	}
}

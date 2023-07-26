package _03;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class ThreadBaseDemo {
	public static void main(String[] args) throws InterruptedException {
		// private native void start0(), JVM_StartThread
		// Thread.java, Thread.c
		// Java 底層為 C++ Language
		Thread t1 = new Thread(() -> System.out.println("This is t1"), "t1");
		t1.start();

		Object o = new Object();
		new Thread(()->{
			synchronized (o){
				System.out.println();
			}
		},"t2");
	}
}

package com.lex.thread.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lex Yu
 * @date : 2023/7/25
 */
public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

		// First
		System.out.println(blockingQueue.add("a")); // return true
		System.out.println(blockingQueue.add("b"));
		System.out.println(blockingQueue.add("c"));
		System.out.println(blockingQueue.element());
//		System.out.println(blockingQueue.add("q")); // throw exception
		System.out.println(blockingQueue.remove()); // a
		System.out.println(blockingQueue.remove()); // b
		System.out.println(blockingQueue.remove()); // c
//		System.out.println(blockingQueue.remove()); // throw exception

		// Second
		System.out.println(blockingQueue.offer("a")); // return true
		System.out.println(blockingQueue.offer("b"));
		System.out.println(blockingQueue.offer("c"));
		System.out.println(blockingQueue.offer("qqqq")); // return false
		System.out.println(blockingQueue.peek()); // a
		System.out.println(blockingQueue.poll()); // a
		System.out.println(blockingQueue.poll()); // b
		System.out.println(blockingQueue.poll()); // c
		System.out.println(blockingQueue.poll()); // null

		// Third
		blockingQueue.put("a");
		blockingQueue.put("b");
		blockingQueue.put("c");
//		blockingQueue.put("pppp"); // always blocking
		blockingQueue.take();
		blockingQueue.take();
		blockingQueue.take();
//		blockingQueue.take(); // always blocking

		// Fourth
		System.out.println(blockingQueue.offer("a"));
		System.out.println(blockingQueue.offer("b"));
		System.out.println(blockingQueue.offer("c"));
		System.out.println(blockingQueue.offer("llll", 3, TimeUnit.SECONDS)); // over 3 secs, will exit


	}
}

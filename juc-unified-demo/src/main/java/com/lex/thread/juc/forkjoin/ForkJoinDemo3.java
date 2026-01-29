package com.lex.thread.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class ForkJoinDemo3 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// Summing numbers from 0 to 100 using ForkJoin
		MyTask myTask = new MyTask(0, 100);
		// Create Fork Join Pool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
		
		// Join Result
		Integer result = forkJoinTask.get();
		System.out.println("Fork Join Result = " + result);
		forkJoinPool.shutdown();
	}
}

class MyTask extends RecursiveTask<Integer> {

	// Threshold for splitting task
	private static final Integer VALUE = 10;
	private int begin;
	private int end;
	private int result;

	public MyTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	// fork and join process
	@Override
	protected Integer compute() {
		// If range is small enough, compute directly
		if ((end - begin) <= VALUE) {
			for (int i = begin; i <= end; i++) {
				result = result + i;
			}
		} else {
			// Split task into two sub-tasks
			int middle = (begin + end) / 2;
			MyTask task01 = new MyTask(begin, middle);
			MyTask task02 = new MyTask(middle + 1, end);
			// Fork sub-tasks
			task01.fork();
			task02.fork();
			// Join Results from sub-tasks
			result = task01.join() + task02.join();
		}
		return result;
	}
}
package com.lex.practice.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author : Lex Yu
 * @date : 2023/7/26
 */
public class ForkJoinDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 1+2+3+4+5+......+100
		// [0,100] -> [0,50] , [51,100] -> [0,25] , [26,50] , [51,75] , [76,100] ->
		// [0,12], [13,25] , [26,38] , [39,50] , [51,63] , [64,75] , [76,88] , [89,100] ->
		//
		MyTask myTask = new MyTask(0, 100);
		// Create Fork Join Pool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
		// 取得最終Join Result
		Integer result = forkJoinTask.get();
		System.out.println("Fork Join Result = " + result);
		forkJoinPool.shutdown();
	}
}

class MyTask extends RecursiveTask<Integer> {

	// Fork, diff = 10
	private static final Integer VALUE = 10;
	private int begin; // fork 起始值
	private int end; // fork 結束值
	private int result; // return join task result

	public MyTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	// fork and join process
	@Override
	protected Integer compute() {
		// 判斷相加的兩個數值差值是否大於10
		if ((end - begin) <= VALUE) {
			// 進行操作
			for (int i = begin; i <= end; i++) {
				result = result + i;
			}
		} else {
			// 進一步 Fork
			// 取得中間值
			int middle = (begin + end) / 2;
			// 中間值左邊部分 -> Task01
			MyTask task01 = new MyTask(begin, middle);
			// 中間值右邊部分 -> Task02
			MyTask task02 = new MyTask(middle + 1, end);
			// 呼叫fork()
			task01.fork();
			task02.fork();
			// Join Result
			result = task01.join() + task02.join();
		}
		return result;
	}
}

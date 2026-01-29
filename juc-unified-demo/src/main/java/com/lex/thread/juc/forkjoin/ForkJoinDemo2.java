package com.lex.thread.juc.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author : Lex Yu
 */
public class ForkJoinDemo2 {
	/*
	Having a list of integers, get back the sum for all the values
	[1,2,3,4,5,6,7,...etc.] ===> 28

  f(1)  f(2)  f(3)  f(4)  f(5)  f(6)  f(7)
  1,    1,    2,    3,    5,    8,    13 ...

  f(7) =  13

  f(n) = f(n-1) + f(n-2)

  n-1 = k

  f(n-1) = f(k) = f(k-1) + f(k-2)
	 */
	public static void main(String[] args) {
		var list = new ArrayList<Long>();

		long endValue = 500_000_000;
		for (long i = 1; i <= endValue; i++) {
			list.add(i);
		}
		long start = System.nanoTime();


//		long res = list.stream().mapToLong(x -> x).sum();
//		System.out.println(res);

		ForkJoinPool pool = new ForkJoinPool();
		Long result = pool.invoke(new SumNumberRecursiveTask(list));
		System.out.println(result);

		long end = System.nanoTime();
		System.out.println("Total Time = " + (end - start) / 1000_000_000.0);
	}
}

class SumNumberRecursiveTask extends RecursiveTask<Long> {
	private final List<Long> input;

	public SumNumberRecursiveTask(List<Long> input) {
		this.input = input;
	}

	@Override
	protected Long compute() {
		if (input.size() <= 1_000_000) {
			return input.stream().mapToLong(x -> x).sum();
		} else {
			System.out.println("---------------------------------------------");
			System.out.println("| Thread = " + Thread.currentThread().getName());
			System.out.println("| Splitting Task ...");
			System.out.println("---------------------------------------------");

			int mid = input.size() / 2; // middle index
			List<Long> list1 = input.subList(0, mid);
			List<Long> list2 = input.subList(mid, input.size());

			var t1 = new SumNumberRecursiveTask(list1);
			var t2 = new SumNumberRecursiveTask(list2);

			t1.fork(); // t1 to be executed on a separate thread
//			t2.fork(); // t2 to be executed on a separate thread

			return t1.join() + t2.compute();
		}
	}
}

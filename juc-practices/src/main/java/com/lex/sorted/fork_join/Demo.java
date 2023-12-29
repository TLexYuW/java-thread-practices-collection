package com.lex.sorted.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author : Lex Yu
 */
public class Demo {
	/*
	ForkJoinPool -> Thread Pool
	Task -> T1 (T1.1, T1.2), T2 (T2.1, T2.2)
	-> RecursiveAction ====> Runnable
	-> RecursiveTask<T> ====> Callable<T>

	Scenario:
	Having a List, print in the console the values from the list doubled
	[1,2,3,4,5,6,7,8,9,10,11,12,13,14,...etc.]
	[1,2,3,4,5,6,7], [8,9,10,11,12,13,14,...etc.]
	[1,2,3],[4,5,6,7],[8,9,10],[11,12,13,14,...etc.]
	[1],[2, 3],[4, 5],[6, 7],[8],[9, 10],[11, 12],[13, 14] ...etc.
	 */
	public static void main(String[] args) {

		var list = new ArrayList<Long>();

		long endValue = 100_000_000;
		for (long i = 1; i <= endValue; i++) {
			list.add(i);
		}
		long start = System.nanoTime();

//		ForkJoinPool pool = new ForkJoinPool();
//		pool.invoke(new DoubleNumbersRecursiveAction(list)); // 592

//		list.stream().map(n -> 2 * n).forEach(n -> System.out.println("Doubled num = " + n)); // 369

//		var doubleNumbersRecursiveAction = new DoubleNumbersRecursiveAction(list);
//		doubleNumbersRecursiveAction.compute(); // 594

		long end = System.nanoTime();
		System.out.println("Total Time = " + (end - start) / 1000_000_000.0);
	}
}

class DoubleNumbersRecursiveAction extends RecursiveAction {
	private final List<Long> input;

	public DoubleNumbersRecursiveAction(List<Long> input) {
		this.input = input;
	}

	@Override
	protected void compute() {
		if (input.size() < 3) {
			input.stream().map(n -> 2 * n).forEach(n -> System.out.println("Doubled num = " + n));
		} else {
			System.out.println("---------------------------------------------");
			System.out.println("| Thread = " + Thread.currentThread().getName());
			System.out.println("| Splitting Task ...");
			System.out.println("---------------------------------------------");

			int mid = input.size() / 2; // middle index
			List<Long> list1 = input.subList(0, mid);
			List<Long> list2 = input.subList(mid, input.size());

			var t1 = new DoubleNumbersRecursiveAction(list1);
			var t2 = new DoubleNumbersRecursiveAction(list2);

			invokeAll(t1, t2);
		}
	}
}
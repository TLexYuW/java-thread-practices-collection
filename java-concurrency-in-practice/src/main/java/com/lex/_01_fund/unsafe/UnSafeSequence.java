package com.lex._01_fund.unsafe;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Lex Yu
 * @date : 2023/9/6
 */
@NotThreadSafe
public class UnSafeSequence {
	private int value;

	/**
	 * Returns a unique value.
	 */
	public void getNext() {
		value++;
	}

	public int getValue(){
		return value;
	}
}

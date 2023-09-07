package com.lex._01_fund.own_test;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Lex Yu
 * @date : 2023/9/7
 */
@ThreadSafe
public class AtomicSequence {
	private AtomicInteger value = new AtomicInteger(0);

	/**
	 * Returns a unique value.
	 */
	public void getNext() {
		value.incrementAndGet();
	}

	public AtomicInteger getValue(){
		return value;
	}
}

package com.lex._01_fund.safe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author : Lex Yu
 * @date : 2023/9/6
 */

@ThreadSafe
public class Sequence {
	@GuardedBy("this") private int nextValue;

	public synchronized int getNext() {
		return nextValue++;
	}

	public int getValue(){
		return nextValue;
	}
}
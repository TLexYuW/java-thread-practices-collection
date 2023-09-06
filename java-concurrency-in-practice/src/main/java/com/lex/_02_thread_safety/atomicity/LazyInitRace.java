package com.lex._02_thread_safety.atomicity;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author : Lex Yu
 * @date : 2023/9/6
 */
@NotThreadSafe
public class LazyInitRace {
	private ExpensiveObject instance = null;

	// plus synchronized, make it thread-safety
	public ExpensiveObject getInstance() {
		if (instance == null)
			instance = new ExpensiveObject();
		return instance;
	}
}

class ExpensiveObject { }


package com.lex.practice.lock;

/**
 * @author : LEX_YU
 * @date : 14/03/2023
 */
public class CounterSynchronized {
    private long count = 0;

    public synchronized void inc() {
        this.count++;
    }

    public synchronized long getCount() {
        return this.count;
    }
}

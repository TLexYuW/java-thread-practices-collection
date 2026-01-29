package com.lex.thread.issues.race;

/**
 * @author : LEX_YU
 * @date : 22/02/2023 11:45 pm
 */
public class CounterSynchronized {
    private long count = 0;

    public long incAndGet() {
        synchronized (this) {
            this.count++;
            return this.count;
        }
    }

    public long get() {
        synchronized (this) {
            return this.count;
        }
    }
}

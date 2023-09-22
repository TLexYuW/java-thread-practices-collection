package com.lex.practice.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @date : 14/03/2023
 */
public class CounterLock {
    private long count = 0;
    private Lock lock = new ReentrantLock();

    private void inc() {
        try {
            lock.lock();
            this.count++;
        } finally {
            lock.unlock();
        }
    }

    public long getCount() {
        try {
            lock.lock();
            return this.count;
        } finally {
            lock.unlock();
        }
    }

}

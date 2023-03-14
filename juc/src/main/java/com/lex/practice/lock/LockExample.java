package com.lex.practice.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @date : 14/03/2023
 */
public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(); // default unfairness
//        Lock lock = new ReentrantLock(true); // fairness
        lock.lock();

        // do something

        lock.unlock();
    }
}

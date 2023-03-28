package com.lex.practice.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @date : 28/03/2023
 */
public class DeadlockExample {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Runnable runnable1 = new Runnable1(lock1, lock2);
        Runnable runnable2 = new Runnable2(lock1, lock2);

        Runnable runnableS1 = new RunnableSync1(lock1,lock2);
        Runnable runnableS2 = new RunnableSync2(lock1,lock2);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
//        thread1.start();
//        thread2.start();

        Thread threadS1 = new Thread(runnableS1);
        Thread threadS2 = new Thread(runnableS2);
        threadS1.start();
        threadS2.start();

    }
}

package com.lex.practice.deadlock;

import java.util.concurrent.locks.Lock;

/**
 * @author : LEX_YU
 * @date : 28/03/2023
 */
public class RunnableSync1 implements Runnable{
    private Lock lock1;
    private Lock lock2;

    public RunnableSync1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " attempting to lock Lock 1");
        synchronized (lock1){
            System.out.println(threadName + " locked Lock 1");

            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(threadName + " attempting to lock Lock 2");
            synchronized (lock2){
                System.out.println(threadName + " locked Lock 2");
            }
            System.out.println(threadName + "unlocking Lock 2");
            lock2.unlock();
        }

        // do the work - now that both locks have been acquired
        System.out.println(threadName + "unlocking Lock 1");
        lock1.unlock();

    }
}

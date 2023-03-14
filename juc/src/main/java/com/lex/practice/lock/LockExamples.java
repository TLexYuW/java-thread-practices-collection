package com.lex.practice.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LEX_YU
 * @date : 14/03/2023
 */
public class LockExamples {
    public static void main(String[] args) {
//        lockBasics();
//        lockInterruptibly();
//        tryLock();
        ReentrantLock lock = new ReentrantLock();
        int holdCount = lock.getHoldCount();
        int queueLength = lock.getQueueLength();
        boolean hasQueueThisThread = lock.hasQueuedThread(Thread.currentThread());
        boolean hasQueuedThisThread = lock.hasQueuedThreads();
        boolean isFair = lock.isFair();
        boolean isLocked = lock.isLocked();
        boolean isHeldByCurrentThread = lock.isHeldByCurrentThread();
    }

    private static void lockBasics() {
        Lock lock = new ReentrantLock(false);
        Runnable runnable = () -> lockSleepUnlock(lock, 1000);
        Thread thread1 = new Thread(runnable, "Thread 1");
        Thread thread2 = new Thread(runnable, "Thread 2");
        Thread thread3 = new Thread(runnable, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void tryLock() {
        /* this version, even set fairness true, tryLock() will not respect
        Lock lock = new ReentrantLock(true);
        try {
            boolean lockSuccessful = lock.tryLock();
            System.out.println("Lock successful: " + lockSuccessful);
        } finally {
            lock.unlock();
        }
         */

        Lock lock = new ReentrantLock(true);
        try {
            // setTimeout
            boolean lockSuccessful = lock.tryLock(1000, TimeUnit.SECONDS);
            System.out.println("Lock successful: " + lockSuccessful);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    private static void lockInterruptibly() {
        Lock lock = new ReentrantLock();
        Thread.currentThread().interrupt();
        try {
            lock.lockInterruptibly();
            lock.unlock();
        } catch (InterruptedException ex) {
            System.out.println("Thread interrupted");
        }
    }

    private static void lockSleepUnlock(Lock lock, long timeMillis) {
        try {
            lock.lock();
            printThreadMsg(" Hold The Lock.");
            sleep(timeMillis);
        } finally {
            lock.unlock();
        }
    }

    private static void printThreadMsg(String text) {
        System.out.println(Thread.currentThread().getName() + " " + text);
    }

    private static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

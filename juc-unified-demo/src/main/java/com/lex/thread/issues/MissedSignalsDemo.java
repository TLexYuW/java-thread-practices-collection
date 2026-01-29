package com.lex.thread.issues;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MissedSignalsDemo {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean isNotified = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waitingThread = new Thread(() -> {
            lock.lock();
            try {
                while (!isNotified) {
                    condition.await(); // ??
                }
                System.out.println("???");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Thread signalingThread = new Thread(() -> {
            lock.lock();
            try {
                isNotified = true; // ??
                // condition.signal(); // ? waitingThread ????????
            } finally {
                lock.unlock();
            }
        });

        waitingThread.start();
        Thread.sleep(100); // ? waitingThread ????
        signalingThread.start();

        waitingThread.join();
        signalingThread.join();
    }
}

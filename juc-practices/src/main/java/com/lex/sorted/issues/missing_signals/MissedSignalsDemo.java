package com.lex.sorted.issues.missing_signals;

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
                    condition.await(); // 等待通知
                }
                System.out.println("收到通知了！");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        Thread signalingThread = new Thread(() -> {
            lock.lock();
            try {
                isNotified = true; // 設置通知標誌
                // condition.signal(); // 如果 waitingThread 還未在等待，就會錯過信號
            } finally {
                lock.unlock();
            }
        });

        waitingThread.start();
        Thread.sleep(100); // 確保 waitingThread 先啟動
        signalingThread.start();

        waitingThread.join();
        signalingThread.join();
    }
}

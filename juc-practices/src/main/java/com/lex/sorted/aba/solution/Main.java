package com.lex.sorted.aba.solution;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : LEX_YU
 */
public class Main {
    // 初始化餘額為100
    static AtomicStampedReference<Integer> balance = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        int initialBalance = balance.getReference();
        int stamp = balance.getStamp();

        // 線程一: 取得原始餘額 100 並因取款更新為 50
        Thread thread1 = new Thread(() -> {
            // 取款前的餘額
            System.out.println("Thread 1 - t1Balance: " + initialBalance);
            System.out.println("Thread 1 - t1Stamp: " + stamp);
            // 模擬取款操作
            try {
                Thread.sleep(1000); // 取得餘額100
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 進行取款操作
            boolean success = balance.compareAndSet(initialBalance, initialBalance - 50, stamp, stamp + 1);

            System.out.println("Thread 1 - Withdrawal status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 1 - after Withdrawal : " + balance.getReference());
            System.out.println("Thread 1 - new stamp : " + balance.getStamp());

        });

        // 線程二 : 取得原始餘額 100 並更新為 50，但取得原始餘額後Block
        Thread thread2 = new Thread(() -> {
            // 取得轉帳前的餘額
            // 模擬取款操作
            try {
                Thread.sleep(1000); // 取得餘額100
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 - t2Balance: " + initialBalance);
            System.out.println("Thread 2 - t2Stamp: " + stamp);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean success = balance.compareAndSet(initialBalance, initialBalance - 50, stamp, stamp + 1);
            System.out.println("Thread 2 - Withdrawal status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 2 - final balance = " + initialBalance);
        });

        // 線程三 : 取得線程一修改後餘額 50，並更新他人轉帳50後餘額
        Thread thread3 = new Thread(() -> {
            // 模擬轉帳操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int currentBalance = balance.getReference();
            int currentStamp = balance.getStamp();

            System.out.println("Thread 3 - t3Balance: " + currentBalance);
            System.out.println("Thread 3 - t3Stamp: " + currentStamp);

            boolean success = balance.compareAndSet(currentBalance, currentBalance + 50, currentStamp, currentStamp + 1);
            System.out.println("Thread 3 - Transfer status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 3 - transfer 50 from someone, final balance = " + currentBalance);
        });

        // 啟動線程
        thread1.start();
        thread2.start();
        thread3.start();

        // 等待線程執行完畢
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 最終餘額
        System.out.println("Final Balance: " + initialBalance);
        System.out.println("Final Stamp: " + stamp);
    }
}
/*
 Thread 1 - t1Balance: 100
 Thread 1 - t1Stamp: 0
 Thread 2 - t2Balance: 100
 Thread 1 - Withdrawal status: Success
 Thread 2 - t2Stamp: 0
 Thread 1 - after Withdrawal : 50
 Thread 1 - new stamp : 1
 Thread 3 - t3Balance: 50
 Thread 3 - t3Stamp: 1
 Thread 3 - Transfer status: Success
 Thread 3 - transfer 50 from someone, final balance = 50
 Thread 2 - Withdrawal status: Failed
 Thread 2 - final balance = 100
 Final Balance: 100
 Final Stamp: 0
 */
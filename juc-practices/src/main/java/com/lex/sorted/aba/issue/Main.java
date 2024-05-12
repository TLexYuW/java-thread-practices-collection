package com.lex.sorted.aba.issue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : LEX_YU
 */
public class Main {
    public static void main(String[] args) {
        // 初始化餘額為 100
        AtomicInteger balance = new AtomicInteger(100);

        // 線程一: 取得原始餘額 100 並更新為 50
        Thread thread1 = new Thread(() -> {
            // 取款前的餘額
            int initialBalance = balance.get();
            System.out.println("Thread 1 - Initial balance: " + initialBalance);

            // 模擬取款操作
            try {
                Thread.sleep(1000); // 取得餘額100
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 進行取款操作
            boolean success = balance.compareAndSet(initialBalance, initialBalance - 50);
            System.out.println("Thread 1 - Withdrawal status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 1 - Final Balance: " + balance.get());
        });

        // 線程二 : 取得原始餘額 100 並更新為 50，但取得原始餘額後Block
        Thread thread2 = new Thread(() -> {
            // 取得轉帳前的餘額
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int initialBalance = balance.get(); // 取得餘額 100
            System.out.println("Thread 2 - Initial balance: " + initialBalance);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance.compareAndSet(initialBalance, initialBalance - 50);
            System.out.println("Thread 2 - after withdrawal, final balance = " + balance.get());
        });

        // 線程三 : 取得線程一修改後餘額 50，並更新他人轉帳50後餘額
        Thread thread3 = new Thread(() -> {
            // 模擬轉帳操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int initialBalance = balance.get(); // 取得餘額 50
            System.out.println("Thread 3 - Initial balance: " + initialBalance);

            balance.compareAndSet(initialBalance, initialBalance + 50);

            System.out.println("Thread 3 - transfer 50 from someone, final balance = " + balance.get());
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
        if (balance.get() == 100) {
            System.out.println("Final balance: " + balance.get());
        } else {
            System.out.println("Final balance should be 100 but " + balance.get());
        }

    }
}
/*
Thread 1 - Initial balance: 100
Thread 2 - Initial balance: 100
Thread 1 - Withdrawal status: Success
Thread 1 - Final Balance: 50
Thread 3 - Initial balance: 50
Thread 3 - transfer 50 from someone, final balance = 100
Thread 2 - after withdrawal, final balance = 50
Final balance should be 100 but 50
 */
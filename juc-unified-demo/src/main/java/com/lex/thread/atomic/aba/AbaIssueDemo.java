package com.lex.thread.atomic.aba;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : LEX_YU
 */
public class AbaIssueDemo {
    public static void main(String[] args) {
        // Initial balance 100
        AtomicInteger balance = new AtomicInteger(100);

        // Thread 1
        Thread thread1 = new Thread(() -> {
            int initialBalance = balance.get();
            System.out.println("Thread 1 - Initial balance: " + initialBalance);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean success = balance.compareAndSet(initialBalance, initialBalance - 50);
            System.out.println("Thread 1 - Withdrawal status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 1 - Final Balance: " + balance.get());
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int initialBalance = balance.get(); 
            System.out.println("Thread 2 - Initial balance: " + initialBalance);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance.compareAndSet(initialBalance, initialBalance - 50);
            System.out.println("Thread 2 - after withdrawal, final balance = " + balance.get());
        });

        // Thread 3
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int initialBalance = balance.get(); 
            System.out.println("Thread 3 - Initial balance: " + initialBalance);

            balance.compareAndSet(initialBalance, initialBalance + 50);

            System.out.println("Thread 3 - transfer 50 from someone, final balance = " + balance.get());
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + balance.get());
    }
}
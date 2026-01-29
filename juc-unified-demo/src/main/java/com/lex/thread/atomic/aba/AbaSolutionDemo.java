package com.lex.thread.atomic.aba;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : LEX_YU
 */
public class AbaSolutionDemo {
    // Initial balance 100
    static AtomicStampedReference<Integer> balance = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        int initialBalance = balance.getReference();
        int stamp = balance.getStamp();

        // Thread 1
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 - t1Balance: " + initialBalance);
            System.out.println("Thread 1 - t1Stamp: " + stamp);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean success = balance.compareAndSet(initialBalance, initialBalance - 50, stamp, stamp + 1);

            System.out.println("Thread 1 - Withdrawal status: " + (success ? "Success" : "Failed"));
            System.out.println("Thread 1 - after Withdrawal : " + balance.getReference());
            System.out.println("Thread 1 - new stamp : " + balance.getStamp());

        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000); 
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
            System.out.println("Thread 2 - final balance = " + balance.getReference());
        });

        // Thread 3
        Thread thread3 = new Thread(() -> {
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
            System.out.println("Thread 3 - transfer 50 from someone, final balance = " + balance.getReference());
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

        System.out.println("Final Balance: " + balance.getReference());
        System.out.println("Final Stamp: " + balance.getStamp());
    }
}
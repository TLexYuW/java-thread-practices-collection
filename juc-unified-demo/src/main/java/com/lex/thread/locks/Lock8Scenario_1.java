package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_1 {

    public synchronized void sendEmail() {
        System.out.println("------> Send Email");
    }

    public synchronized void sendSMS() {
        System.out.println("------> Send SMS");
    }
}

/**
 * 1. Standard Access : two threads
 */
public class Lock8Scenario_1 {
    public static void main(String[] args) {

        Phone_1 p = new Phone_1();
        new Thread(() -> {
            System.out.println("T : " + Thread.currentThread().getName());
            p.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("T : " + Thread.currentThread().getName());
            p.sendSMS();
        }, "B").start();

    }
}

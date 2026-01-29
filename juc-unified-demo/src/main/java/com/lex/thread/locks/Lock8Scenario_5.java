package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_5 {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("T : " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------> Send Email");
    }

    public static synchronized void sendSMS() {
        System.out.println("T : " + Thread.currentThread().getName());
        System.out.println("------> Send SMS");
    }
}

/**
 * 5. Two static method and one instance
 */
public class Lock8Scenario_5 {
    public static void main(String[] args) {

        Phone_5 p1 = new Phone_5();
        
        new Thread(() -> {
            p1.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p1.sendSMS();
        }, "B").start();

    }
}

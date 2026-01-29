package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_6 {

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
 * 6. Two static method and Two instances
 */
public class Lock8Scenario_6 {
    public static void main(String[] args) {

        Phone_6 p1 = new Phone_6();        
        Phone_6 p2 = new Phone_6();

        new Thread(() -> {
            p1.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p2.sendSMS();
        }, "B").start();

    }
}

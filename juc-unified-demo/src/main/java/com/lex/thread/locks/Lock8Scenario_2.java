package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_2 {

    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("T : " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------> Send Email");
    }

    public synchronized void sendSMS() {
        System.out.println("T : " + Thread.currentThread().getName());
        System.out.println("------> Send SMS");
    }
}

/**
 * 2. SendMail add sleep 3 Sec.
 */
public class Lock8Scenario_2 {
    public static void main(String[] args) {

        Phone_2 p = new Phone_2();
        new Thread(() -> {
            p.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            p.sendSMS();
        }, "B").start();

    }
}

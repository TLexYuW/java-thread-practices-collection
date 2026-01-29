package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_7 {

    public static synchronized void sendEmail() {
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
 * 7. one static sync method and one sync method and one instance
 * Print SMS or Email First?
 */
public class Lock8Scenario_7 {
    public static void main(String[] args) {

        Phone_7 p1 = new Phone_7();        

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

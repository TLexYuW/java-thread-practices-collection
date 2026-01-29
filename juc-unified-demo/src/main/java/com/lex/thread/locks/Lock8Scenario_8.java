package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_8 {

    // Class Lock : ??????????????????
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("T : " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------> Send Email");
    }

    // Object Lock : ???????????
    public synchronized void sendSMS() {
        System.out.println("T : " + Thread.currentThread().getName());
        System.out.println("------> Send SMS");
    }
}

/**
 * 8. one static sync method and one sync method and two instances
 * Print SMS or Email First?
 */
public class Lock8Scenario_8 {
    public static void main(String[] args) {

        Phone_8 p1 = new Phone_8();        
        Phone_8 p2 = new Phone_8();        

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

package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_3 {

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

    public void hi(){
        System.out.println("T : " + Thread.currentThread().getName());
        System.out.println("-------> hi");
    }
}

/**
 * 3. Add Method
 */
public class Lock8Scenario_3 {
    public static void main(String[] args) {

        Phone_3 p = new Phone_3();
        new Thread(() -> {
            p.sendEmail();
        }, "A").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            // p.sendSMS();
            p.hi();
        }, "B").start();

    }
}

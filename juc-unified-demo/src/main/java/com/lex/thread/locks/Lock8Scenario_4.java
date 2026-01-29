package com.lex.thread.locks;

import java.util.concurrent.TimeUnit;

class Phone_4 {

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
 * 4. Two instance
 */
public class Lock8Scenario_4 {
    public static void main(String[] args) {

        Phone_4 p1 = new Phone_4();
        Phone_4 p2 = new Phone_4();
        
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

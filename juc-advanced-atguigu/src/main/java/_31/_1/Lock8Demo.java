package _31._1;

import java.util.concurrent.TimeUnit;

class Phone {

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
public class Lock8Demo {
    public static void main(String[] args) {

        Phone p = new Phone();
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

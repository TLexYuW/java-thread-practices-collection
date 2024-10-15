package _31._7;

import java.util.concurrent.TimeUnit;

class Phone {

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
public class Lock8Demo {
    public static void main(String[] args) {

        Phone p1 = new Phone();        

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

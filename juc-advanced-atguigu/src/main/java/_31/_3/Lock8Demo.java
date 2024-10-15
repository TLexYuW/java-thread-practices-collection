package _31._3;

import java.util.concurrent.TimeUnit;

class Phone {

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
public class Lock8Demo {
    public static void main(String[] args) {

        Phone p = new Phone();
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

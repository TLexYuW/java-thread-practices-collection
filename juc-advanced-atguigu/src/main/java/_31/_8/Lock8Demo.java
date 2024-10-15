package _31._8;

import java.util.concurrent.TimeUnit;

class Phone {

    // Class Lock : 用於靜態方法，鎖定類別本身，控制對類別級別的訪問。
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("T : " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------> Send Email");
    }

    // Object Lock : 用於實例方法，鎖定特定物件，控制對該物件的訪問。
    public synchronized void sendSMS() {
        System.out.println("T : " + Thread.currentThread().getName());
        System.out.println("------> Send SMS");
    }
}

/**
 * 8. one static sync method and one sync method and two instances
 * Print SMS or Email First?
 */
public class Lock8Demo {
    public static void main(String[] args) {

        Phone p1 = new Phone();        
        Phone p2 = new Phone();        

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

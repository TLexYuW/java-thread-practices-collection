package com.lex.thread.issues;

public class SlippedConditionsDemo {
    private volatile int counter = 0;

    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            counter++;
            System.out.println("Counter incremented: " + counter);

            // ???????? counter = 5 ????
            if (counter == 5) {
                System.out.println("Condition met: counter is 5, executing operation...");
            }
        }
    }

    public void checkCondition() {
        synchronized (lock) {
            // ?????
            if (counter == 5) {
                System.out.println("Check condition: counter is still 5.");
            }
        }
    }

    public static void main(String[] args) {
        SlippedConditionsDemo demo = new SlippedConditionsDemo();

        // ??????????
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.increment();
                try {
                    Thread.sleep(50); // ?
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.checkCondition(); // 
                try {
                    Thread.sleep(50); // ?
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + demo.counter);
    }
}

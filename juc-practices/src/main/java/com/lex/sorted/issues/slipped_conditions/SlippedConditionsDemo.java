package com.lex.sorted.issues.slipped_conditions;

public class SlippedConditionsDemo {
    private volatile int counter = 0;

    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            counter++;
            System.out.println("Counter incremented: " + counter);

            // 模擬一個條件檢查，假設我們希望在 counter = 5 時執行某些操作
            if (counter == 5) {
                System.out.println("Condition met: counter is 5, executing operation...");
            }
        }
    }

    public void checkCondition() {
        synchronized (lock) {
            // 這是另一個方法來檢查條件
            if (counter == 5) {
                System.out.println("Check condition: counter is still 5.");
            }
        }
    }

    public static void main(String[] args) {
        SlippedConditionsDemo demo = new SlippedConditionsDemo();

        // 創建兩個線程，將同時增量計數器
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.increment();
                try {
                    Thread.sleep(50); // 模擬一些工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.checkCondition(); // 檢查條件
                try {
                    Thread.sleep(50); // 模擬一些工作
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

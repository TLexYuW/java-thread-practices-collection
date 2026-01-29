package com.lex.thread.sync;

/**
 * 展示目的：展示 volatile 關鍵字如何解決多執行緒間的變數可見性問題 (Visibility Issue)
 * @author : Lex Yu
 * @date : 24/07/2023
 */
public class VolatileVisibilityDemo {

    // Without volatile key word, reader thread might not see the update
    // static int num;

    // Main memory
    static volatile int num;

    public static void main (String[] args) {

        Thread readerThread = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != num) {
                    temp = num;
                    System.out.println("reader: value of num = " + num);
                }
            }
        });

        Thread writerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                num++;
                System.out.println("writer: changed value to = " + num);

                // ???eaderThread?????nt num???num++???????eaderThread????????
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // ????eaderThread??????nt num??            System.exit(0);
        });

        readerThread.start();
        writerThread.start();
    }
}

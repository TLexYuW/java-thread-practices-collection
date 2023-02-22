package com.lex.practice.sync;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 11:39 pm
 */
public class SyncCounterMain {
    public static void main(String[] args) {
        SyncCounter counter = new SyncCounter();

        Thread thread1 = new Thread(() ->{
            for (int i = 0; i < 1_000_000; i++) {
                counter.incCount();
            }
            System.out.println(counter.getCount());
        });

        Thread thread2 = new Thread(() ->{
            for (int i = 0; i < 1_000_000; i++) {
                counter.incCount();
            }
            System.out.println(counter.getCount());
        });

        thread1.start();
        thread2.start();
    }
}

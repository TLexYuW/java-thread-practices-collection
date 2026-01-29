package com.lex.thread.sync.basics;

/**
 * @author : LEX_YU
 * @date : 09/01/2023 9:00 pm
 */
public class SynchronizedExchangerMain {
    public static void main(String[] args) {
        SynchronizedExchanger exchanger =
                new SynchronizedExchanger();

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            exchanger.setObject("T1 " + i);
                        }
                    }
                }
        );

        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            System.out.println("T2 " + exchanger.getObject());
                        }
                    }
                }
        );
        thread1.start();
        thread2.start();

    }
}

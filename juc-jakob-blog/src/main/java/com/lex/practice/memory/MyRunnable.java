package com.lex.practice.memory;

/**
 * @author : LEX_YU
 * @date : 06/01/2023 11:31 pm
 */
public class MyRunnable implements Runnable {
    private int count = 0;
    private Object myObject = null;

    public MyRunnable() {
    }

    public MyRunnable(Object myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
//        Object myObject = new Object();
        System.out.println(myObject);
        synchronized (this) {
            for (int i = 0; i < 1_000_000; i++) {
//                synchronized (this) {
                    this.count++;
//                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " : " + this.count);
    }
}

package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_3 {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }

    public static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("Running ...");
            System.out.println("Finished ...");
        }
    }
}

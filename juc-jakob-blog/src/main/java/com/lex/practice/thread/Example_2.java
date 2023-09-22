package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_2 {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }

    public static class MyThread extends Thread{
        public void run() {
            System.out.println("Running ...");
            System.out.println("Finished ...");
        }
    }
}

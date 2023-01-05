package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_4 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running ...");
                System.out.println("Finished ...");
            }
        };

        Thread thread = new Thread( runnable );
        thread.start();
    }

}

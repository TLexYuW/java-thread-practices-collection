package com.lex.thread.basics;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class ThreadAnonymousRunnableDemo {

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

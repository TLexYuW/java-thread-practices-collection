package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_5 {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Running ...");
            System.out.println("Finished ...");
        };

        Thread thread = new Thread( runnable );
        thread.start();
    }

}

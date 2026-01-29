package com.lex.thread.basics;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class ThreadLambdaRunnableDemo {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Running ...");
            System.out.println("Finished ...");
        };

        Thread thread = new Thread( runnable );
        thread.start();
    }

}

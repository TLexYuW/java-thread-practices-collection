package com.lex.practice.thread;

/**
 * @author : Lex Yu
 */
public class Main2 {
    public static void main(String[] args) {
//        Thread nonDaemonThread = new Thread(() -> {
//            while (true) {
//                System.out.println("Non-daemon thread is running...");
//            }
//        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
//                System.out.println("Daemon thread is running...");
            }
        });

        daemonThread.setDaemon(true);

//        nonDaemonThread.start();
        daemonThread.start();

        // The main method exits here.
        System.out.println("Main exit===========================================================================>");

    }
}

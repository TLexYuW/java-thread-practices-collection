package com.lex.unsorted.virtual_thread._02;

import java.util.stream.IntStream;

/**
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class VTDetachFromPTDemo {
    public static void main(String[] args) throws InterruptedException {
//        Thread.ofPlatform().start(()->{
//            System.out.println("Platform Thread: " + Thread.currentThread());
//        }).join();
//        System.out.println("----------------------------------------------------------------------------------------");
//        Thread.ofVirtual().start(()->{
//            System.out.println("Virtual Thread: " + Thread.currentThread());
//        }).join();

        var threads = IntStream.range(0,11)
                .mapToObj(threadCount -> Thread.ofVirtual().unstarted(()->{
                    if (threadCount == 0){
                        System.out.println("Virtual Thread: " + Thread.currentThread());
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (threadCount == 0){
                        System.out.println("Virtual Thread: " + Thread.currentThread());
                    }
                })).toList();
        threads.forEach(Thread::start);
        for (Thread th : threads) {
            th.join();
        }
    }

    private static Thread virtualThread(String name, Runnable runnable){
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }
}


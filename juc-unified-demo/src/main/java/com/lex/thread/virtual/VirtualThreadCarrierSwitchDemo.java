package com.lex.thread.virtual;

import java.util.stream.IntStream;

/**
 * 展示目的：觀察虛擬執行緒在執行過程中，如何動態掛載與脫離平台載體執行緒 (Carrier Thread)
 * @author : Lex Yu
 * @date : 25/07/2023
 */
public class VirtualThreadCarrierSwitchDemo {
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


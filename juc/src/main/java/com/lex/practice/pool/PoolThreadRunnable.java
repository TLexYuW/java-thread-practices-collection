package com.lex.practice.pool;

import java.util.concurrent.BlockingQueue;

/**
 * @author : LEX_YU
 * @date : 13/03/2023
 */
public class PoolThreadRunnable implements Runnable {
    private Thread thread = null;
    private BlockingQueue<Runnable> taskQueue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> queue) {
        taskQueue = queue;
    }

    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (Exception e) {
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        //break pool thread out of dequeue() call.
        this.thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}

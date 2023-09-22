package com.lex.practice.thread;

/**
 * @author : LEX_YU
 * @date : 05/01/2023 8:53 pm
 */
public class Example_8 {

    public static void main(String[] args) {
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread thread = new Thread(stoppableRunnable, "The Thread");
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Requesting Stop");
        stoppableRunnable.requestStop();
        System.out.println("Stop Requested");
    }

    public static class StoppableRunnable implements Runnable {
        private boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            int count = 1;
            System.out.println("StoppableRunnable Running");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("... " + count++);
            }
            System.out.println("StoppableRunnable Stopped");
        }
    }

}

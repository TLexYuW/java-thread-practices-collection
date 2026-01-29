package com.lex.thread.issues;

public class NestedMonitorLockoutDemo {
    
    static class NonReentrantLock {
        private boolean isLocked = false;
        private Thread lockedBy = null;
        private int waitingThreads = 0;  

        public synchronized void lock() throws InterruptedException {
            if (isLocked && Thread.currentThread() == lockedBy) {
                System.out.println(Thread.currentThread().getName() + " Re-entering lock...");
                waitingThreads++;
                wait();
                waitingThreads--;
            }

            while (isLocked) {
                waitingThreads++;
                wait();
                waitingThreads--;
            }
            isLocked = true;
            lockedBy = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + " Locked (Waiting: " + waitingThreads + ")");
        }

        public synchronized void unlock() {
            if (Thread.currentThread() == lockedBy) {
                isLocked = false;
                lockedBy = null;
                notify();
                System.out.println(Thread.currentThread().getName() + " Unlocked (Waiting: " + waitingThreads + ")");
            }
        }

        public synchronized int getWaitingThreads() {
            return waitingThreads;
        }
    }

    private final NonReentrantLock lock = new NonReentrantLock();
    private int sharedResource = 0;  

    public void doWork() {
        try {
            lock.lock();  
            System.out.println(Thread.currentThread().getName() + " working...");
            sharedResource++;

            Thread.sleep(100);

            try {
                lock.lock();  
                System.out.println(Thread.currentThread().getName() + " working again...");
                sharedResource++;
            } finally {
                lock.unlock();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        } finally {
            lock.unlock();
        }
    }

    public int getSharedResource() {
        return sharedResource;
    }

    public static void main(String[] args) throws InterruptedException {
        NestedMonitorLockoutDemo demo = new NestedMonitorLockoutDemo();

        Thread worker = new Thread(() -> {
            demo.doWork();
        }, "WorkerThread");

        Thread monitor = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    System.out.println("Monitor: sharedResource = " + demo.getSharedResource());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "MonitorThread");

        worker.start();
        monitor.start();

    }
}
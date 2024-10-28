package com.lex.sorted.issues.nested_monitor_lockout;

public class NestedMonitorLockoutDemo {
    // 自定義的非可重入鎖
    static class NonReentrantLock {
        private boolean isLocked = false;
        private Thread lockedBy = null;
        private int waitingThreads = 0;  // 新增：等待線程計數

        public synchronized void lock() throws InterruptedException {
            if (isLocked && Thread.currentThread() == lockedBy) {
                System.out.println(Thread.currentThread().getName() + " 嘗試重入鎖 - 被阻塞");
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
            System.out.println(Thread.currentThread().getName() + " 獲得鎖 (等待中的線程: " + waitingThreads + ")");
        }

        public synchronized void unlock() {
            if (Thread.currentThread() == lockedBy) {
                isLocked = false;
                lockedBy = null;
                notify();
                System.out.println(Thread.currentThread().getName() + " 釋放鎖 (等待中的線程: " + waitingThreads + ")");
            }
        }

        public synchronized int getWaitingThreads() {
            return waitingThreads;
        }
    }

    private final NonReentrantLock lock = new NonReentrantLock();
    private int sharedResource = 0;  // 共享資源

    public void doWork() {
        try {
            lock.lock();  // 第一次獲得鎖
            System.out.println(Thread.currentThread().getName() + " 開始處理共享資源");
            sharedResource++;

            // 模擬一些處理時間
            Thread.sleep(100);

            // 嘗試再次獲得相同的鎖 - 這裡會發生死鎖
            try {
                lock.lock();  // 第二次嘗試獲得相同的鎖
                System.out.println(Thread.currentThread().getName() + " 嘗試進行額外操作");
                sharedResource++;
            } finally {
                lock.unlock();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " 被中斷");
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
                    System.out.println("監控線程: 共享資源當前值 = " + demo.getSharedResource());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "MonitorThread");

        worker.start();
        monitor.start();

        // 等待一段時間後中斷所有線程
//        Thread.sleep(2000);
//        monitor.interrupt();
//        worker.interrupt();
    }
}

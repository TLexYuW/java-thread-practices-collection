package com.lex.thread.issues.race;

/**
 * @author : LEX_YU
 * @date : 23/02/2023 9:23 pm
 * One thread writes, one thread reads
 */
public class RaceConditionsExample3 {
    public static void main(String[] args) {
//        CounterSynchronized counter = new CounterSynchronized();
        Counter counter = new Counter();

        Thread thread1 = new Thread(getIncrementingRunnable(counter));
        Thread thread2 = new Thread(getReadingRunnable(counter));

        thread1.start();
        thread2.start();
    }

    private static Runnable getIncrementingRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.incAndGet();
            }
            System.out.println("Thread1 final count: " + counter.get());
        };
    }

    private static Runnable getReadingRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            System.out.println("Thread2 count: " + counter.get());
            }
        };
    }
}

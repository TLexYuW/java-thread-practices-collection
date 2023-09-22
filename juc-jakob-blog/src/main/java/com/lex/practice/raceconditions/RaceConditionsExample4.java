package com.lex.practice.raceconditions;

/**
 * @author : LEX_YU
 * @date : 23/02/2023 9:47 pm
 * Two threads that access same objects, but not write to the same objects.
 */
public class RaceConditionsExample4 {
    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        Thread thread1 = new Thread(getRunnable(counter1, counter2, "Thread 1"));
        Thread thread2 = new Thread(getRunnable(counter2, counter1, "Thread 2"));

        thread1.start();
        thread2.start();
    }

    private static Runnable getRunnable(Counter counterA, Counter counterB, String runnableName) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counterA.incAndGet();
            }
            System.out.println(runnableName + " final count - counterA: " + counterA.get());
            System.out.println(runnableName + " final count - counterB: " + counterB.get());
        };
    }
}

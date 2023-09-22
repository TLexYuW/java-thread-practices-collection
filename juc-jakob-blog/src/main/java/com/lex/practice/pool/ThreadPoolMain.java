package com.lex.practice.pool;


/**
 * @author : LEX_YU
 * @date : 13/03/2023
 */
public class ThreadPoolMain {
    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(3,10);

        for (int i = 0; i < 10; i++) {
            int taskNo = i;
            threadPool.execute( () -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo ;
                System.out.println(message);
            });
        }
        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();
    }
}

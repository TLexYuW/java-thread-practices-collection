package com.lex.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 展示目的：展示 invokeAll：批量提交多個任務，並等待所有任務完成後統一獲取結果
 * @author : LEX_YU
 * @date : 15/03/2023
 */
public class ExecutorInvokeAllBatchDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callables = new ArrayList<>();
        callables.add(newCallable("Task 1.1"));
        callables.add(newCallable("Task 1.2"));
        callables.add(newCallable("Task 1.3"));


        try {
            List<Future<String>> results = executorService.invokeAll(callables);
            for (Future f : results) {
                System.out.println(f.get());
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        executorService.shutdown();
    }

    private static Callable newCallable(String msg) {
        return new Callable() {
            @Override
            public Object call() throws Exception {
                return Thread.currentThread().getName() + ": " + msg;
            }
        };
    }

}

package com.lex.unsorted.sleep._1;

public class Demo {

    public static void processBatch() {
        try {
            System.out.println("開始處理批次...");
            Thread.sleep(3000); // 模擬工作 3 秒
            System.out.println("批次處理完成");

        } catch (InterruptedException e) {
            // 重要：重設中斷狀態
//            Thread.currentThread().interrupt();
            System.out.println("批次處理被中斷");
        }
    }

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                processBatch();
            }
            System.out.println("工作線程結束");
        });

        worker.start();

        // 主線程休息 5 秒後中斷工作線程
        try {
            Thread.sleep(5000);
            worker.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

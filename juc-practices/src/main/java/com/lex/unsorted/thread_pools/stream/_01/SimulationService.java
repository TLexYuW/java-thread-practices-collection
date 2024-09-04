package com.lex.unsorted.thread_pools.stream._01;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class SimulationService {
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public void simulateProcessing(List<String> sCodes, Map<String, String> cCodes,
                                   List<List<LocalDate>> holidayGroups, List<List<LocalDate>> festivalGroups) throws InterruptedException {
        for (String sCode : sCodes) {
            executor.submit(() -> processCode2(sCode, cCodes, holidayGroups, festivalGroups));
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("Tasks did not finish in 1 minute!");
                executor.shutdownNow(); // 嘗試強制終止
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Tasks did not respond to termination!");
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Awaiting termination was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // 重置中斷狀態
            executor.shutdownNow();
        }
    }

    private void processCode(String sCode, Map<String, String> cCodes,
                             List<List<LocalDate>> holidayGroups, List<List<LocalDate>> festivalGroups) {
        System.out.println("Processing sCode: " + sCode + " in thread: " + Thread.currentThread().getName());

        Stream.concat(
                generateEntitiesStream(sCode, cCodes, holidayGroups, true),
                generateEntitiesStream(sCode, cCodes, festivalGroups, false)
        ).forEach(entity -> System.out.println(entity));
    }

    private void processCode2(String sCode, Map<String, String> cCodes,
                              List<List<LocalDate>> holidayGroups, List<List<LocalDate>> festivalGroups) {
        System.out.println("Processing sCode: " + sCode + " in thread: " + Thread.currentThread().getName());

        for (Map.Entry<String, String> cCodeEntry : cCodes.entrySet()) {
            String cCode = cCodeEntry.getKey();
            String base = cCodeEntry.getValue();
            System.out.println(Thread.currentThread().getName() + " processing cCode: " + cCode + ", base: " + base);

            for (List<LocalDate> group : holidayGroups) {
                for (LocalDate date : group) {
                    System.out.println(Thread.currentThread().getName() + " processing holiday date: " + date);
                    // 實體生成邏輯
                }
            }

            for (List<LocalDate> group : festivalGroups) {
                for (LocalDate date : group) {
                    System.out.println(Thread.currentThread().getName() + " processing festival date: " + date);
                    // 實體生成邏輯
                }
            }
        }
    }

    private Stream<String> generateEntitiesStream(String sCode, Map<String, String> cCodes,
                                                  List<List<LocalDate>> groups, boolean isHoliday) {
        return cCodes.entrySet().stream()
                .flatMap(entry -> groups.stream()
                        .flatMap(group -> group.stream()
                                .map(localDate -> buildEntityString(sCode, entry.getKey(), entry.getValue(), localDate, isHoliday))));
    }

    private String buildEntityString(String sCode, String cCode, String base, LocalDate date, boolean isHoliday) {
        return String.format("Entity: sCode=%s, cCode=%s, base=%s, date=%s, isHoliday=%s",
                sCode, cCode, base, date, isHoliday);
    }


}

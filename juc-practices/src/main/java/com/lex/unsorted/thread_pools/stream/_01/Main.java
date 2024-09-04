package com.lex.unsorted.thread_pools.stream._01;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> sCodes = List.of("S001", "S002", "S003");

        Map<String, String> cCodes = Map.of(
                "C01", "Base1",
                "C02", "Base2"
        );

        List<List<LocalDate>> holidayGroups = List.of(
                List.of(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2)),
                List.of(LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 2))
        );

        List<List<LocalDate>> festivalGroups = List.of(
                List.of(LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 26))
        );

        SimulationService service = new SimulationService();
        service.simulateProcessing(sCodes, cCodes, holidayGroups, festivalGroups);
    }

}

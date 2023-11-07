package com.lex.unsorted.future;

import com.lex.unsorted.database.EmployeeDatabase;
import com.lex.unsorted.dto.Employee;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listCompletableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Executed by: " + Thread.currentThread().getName());
                    return EmployeeDatabase.fetchEmployees();
                }, executor);

        executor.shutdown();
        return listCompletableFuture.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        employees.forEach(System.out::println);

    }
}

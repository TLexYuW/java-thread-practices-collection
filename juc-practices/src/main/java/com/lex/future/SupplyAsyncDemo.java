package com.lex.future;

import com.lex.database.EmployeeDatabase;
import com.lex.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Lex Yu
 */
public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> listCompletableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("Executed by: " + Thread.currentThread().getName());
                    return EmployeeDatabase.fetchEmployees();
                });
        return listCompletableFuture.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        employees.forEach(System.out::println);

    }
}

package com.lex.future;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.dto.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Lex Yu
 */
public class RunAsyncDemo {
	public Void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException, TimeoutException {
		ObjectMapper mapper = new ObjectMapper();
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
			try {
				List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
				});
				// write logic to save list of employee to database
				// repository.saveAll(employees);
				System.out.println("Thread: " + Thread.currentThread().getName());
//				employees.forEach(System.out::println);
				System.out.println(employees.size());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

//		return runAsyncFuture.toCompletableFuture();
//		return runAsyncFuture.get();
		return runAsyncFuture.join();
	}

	public Void saveEmployeesWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException, TimeoutException {
		ObjectMapper mapper = new ObjectMapper();
		Executor executor = Executors.newFixedThreadPool(5);
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
			try {
				List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
				});
				// write logic to save list of employee to database
				// repository.saveAll(employees);
				System.out.println("Thread: " + Thread.currentThread().getName());
//				employees.forEach(System.out::println);
				System.out.println(employees.size());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}, executor);
//		return runAsyncFuture.toCompletableFuture();
//		return runAsyncFuture.get();
		return runAsyncFuture.join();
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		RunAsyncDemo runAsyncDemo = new RunAsyncDemo();

		runAsyncDemo.saveEmployees(new File("juc-practices/emp.json"));
		runAsyncDemo.saveEmployeesWithCustomExecutor(new File("juc-practices/emp.json"));
	}
}

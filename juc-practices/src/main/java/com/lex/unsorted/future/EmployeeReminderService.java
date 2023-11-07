package com.lex.unsorted.future;

import com.lex.unsorted.database.EmployeeDatabase;
import com.lex.unsorted.dto.Employee;

import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author : Lex Yu
 */
public class EmployeeReminderService {
	static ExecutorService executorService = Executors.newFixedThreadPool(3);
	static ExecutorService executorService2 = Executors.newFixedThreadPool(3);
	static ExecutorService executorService3 = Executors.newFixedThreadPool(3);


	// Get All Employees from Database
	// Filter Out All New Joined Employees
	// Check If Training Activity Is Pending For Employee
	// Get Employees Email Id
	// Send Reminder Notification To Employees

	public CompletableFuture<Void> sendReminderToEmployee() {
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
			System.out.println("fetchEmployee : " + Thread.currentThread().getName());
			return EmployeeDatabase.fetchEmployees();
		}, executorService).thenApplyAsync(employees -> {
			System.out.println("filter new joiner employee : " + Thread.currentThread().getName());
			return employees.stream().filter(e -> "TRUE".equals(e.getNewJoiner()))
					.collect(Collectors.toList());
		}, executorService2).thenApplyAsync(employees -> {
			System.out.println("filter training not complete employee : " + Thread.currentThread().getName());
			return employees.stream()
					.filter(employee -> "TRUE".equals(employee.getLearningPending()))
					.collect(Collectors.toList());
		}, executorService3).thenApplyAsync(employees -> {
			System.out.println("get emails : " + Thread.currentThread().getName());
			return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
		}, executorService).thenAcceptAsync((emails) -> {
			System.out.println("send emails : " + Thread.currentThread().getName());
			emails.forEach(EmployeeReminderService::sendEmail);
		}, executorService3);
		return voidCompletableFuture;
	}

	public static void sendEmail(String email) {
		System.out.println("Sending Training Reminder Email to : " + email);
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		EmployeeReminderService employeeReminderService = new EmployeeReminderService();
		employeeReminderService.sendReminderToEmployee().get();
		executorService.shutdown();
		executorService2.shutdown();
		executorService3.shutdown();
	}
}

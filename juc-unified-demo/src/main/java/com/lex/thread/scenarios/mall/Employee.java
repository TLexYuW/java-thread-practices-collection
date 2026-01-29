package com.lex.thread.scenarios.mall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lex Yu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String newJoiner;
	private String learningPending;
	private int salary;
	private int rating;
}

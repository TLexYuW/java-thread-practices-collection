package com.lex.unsorted.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.unsorted.dto.Employee;

import java.io.File;
import java.util.List;

/**
 * @author : Lex Yu
 */
public class EmployeeDatabase {

    public static List<Employee> fetchEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Employee> employees = mapper.readValue(new File("juc-practices/emp.json"), new TypeReference<>() {
            });
            System.out.println("Size = " + employees.size());
            return employees;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

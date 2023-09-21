package com.lex.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.dto.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : Lex Yu
 */
public class EmployeeDatabase {

    public static List<Employee> fetchEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("juc-practices/emp.json"), new TypeReference<List<Employee>>() {
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

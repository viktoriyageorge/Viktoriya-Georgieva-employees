package com.sirma.vicky.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeProject {
    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}

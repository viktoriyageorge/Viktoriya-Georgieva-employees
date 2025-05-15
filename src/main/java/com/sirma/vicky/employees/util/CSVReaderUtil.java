package com.sirma.vicky.employees.util;

import com.sirma.vicky.employees.model.EmployeeProject;
import com.sirma.vicky.employees.util.DateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReaderUtil {

    private final DateParser dateParser;

    @Autowired
    public CSVReaderUtil(DateParser dateParser) {
        this.dateParser = dateParser;
    }

    public List<EmployeeProject> readEmployeeProjects(InputStream inputStream) throws IOException {
        List<EmployeeProject> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                int empId = Integer.parseInt(parts[0]);
                int projectId = Integer.parseInt(parts[1]);
                LocalDate dateFrom = dateParser.parse(parts[2]);
                LocalDate dateTo = dateParser.parse(parts[3]);
                records.add(new EmployeeProject(empId, projectId, dateFrom, dateTo));
            }
        }

        return records;
    }
}


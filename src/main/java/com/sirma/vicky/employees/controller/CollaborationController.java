package com.sirma.vicky.employees.controller;

import com.sirma.vicky.employees.model.EmployeeProject;
import com.sirma.vicky.employees.service.CollaborationService;
import com.sirma.vicky.employees.util.CSVReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import src.main.java.com.sirma.vicky.employees.model.CollaborationResult;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/employees")
public class CollaborationController {

    private final CollaborationService service;
    private final CSVReaderUtil csvReaderUtil;

    @Autowired
    public CollaborationController(CollaborationService service, CSVReaderUtil csvReaderUtil) {
        this.service = service;
        this.csvReaderUtil = csvReaderUtil;
    }

    @PostMapping("/upload")
    public ResponseEntity<CollaborationResult> getLongestCollaboration(@RequestParam("file") MultipartFile file) {
        try {
            List<EmployeeProject> projects = csvReaderUtil.readEmployeeProjects(file.getInputStream());
            CollaborationResult result = service.findLongestCollaboration(projects);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}


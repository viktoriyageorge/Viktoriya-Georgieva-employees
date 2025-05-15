package com.sirma.vicky.employees.service;

import com.sirma.vicky.employees.model.EmployeeProject;
import org.springframework.stereotype.Service;
import src.main.java.com.sirma.vicky.employees.model.CollaborationResult;
import src.main.java.com.sirma.vicky.employees.model.Pair;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollaborationService {

    public CollaborationResult findLongestCollaboration(List<EmployeeProject> projects) {
        return projects.stream()
                .collect(Collectors.groupingBy(EmployeeProject::getProjectId))
                .values().stream()
                .flatMap(projectGroup -> {
                    List<Pair<EmployeeProject, EmployeeProject>> pairs = new ArrayList<>();
                    for (int i = 0; i < projectGroup.size(); i++) {
                        for (int j = i + 1; j < projectGroup.size(); j++) {
                            pairs.add(Pair.of(projectGroup.get(i), projectGroup.get(j)));
                        }
                    }
                    return pairs.stream();
                })
                .map(pair -> {
                    EmployeeProject e1 = pair.getLeft();
                    EmployeeProject e2 = pair.getRight();
                    LocalDate overlapStart = Collections.max(List.of(e1.getDateFrom(), e2.getDateFrom()));
                    LocalDate overlapEnd = Collections.min(List.of(e1.getDateTo(), e2.getDateTo()));
                    long days = overlapStart.isAfter(overlapEnd) ? 0 : ChronoUnit.DAYS.between(overlapStart, overlapEnd);
                    return new AbstractMap.SimpleEntry<>(
                            new int[]{e1.getEmpId(), e2.getEmpId()}, days
                    );
                })
                .filter(entry -> entry.getValue() > 0)
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(entry -> new CollaborationResult(
                        entry.getKey()[0], entry.getKey()[1], entry.getValue()
                ))
                .orElse(new CollaborationResult(0, 0, 0));
    }
}

package src.main.java.com.sirma.vicky.employees.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollaborationResult {
    private int empId1;
    private int empId2;
    private long daysWorkedTogether;
}

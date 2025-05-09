package de.szut.bonuscalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class Employee {
    private String name;
    private int yearsOfService;  // TODO: rename drawio diagram: yearsInCompany -> yearsOfService
    private int performanceRating; // 0-10
    private int completedProjects;  // TODO: rename drawio diagram: projectCompleted -> completedProjects
    private int sickDays;
    private boolean isTeamLeader;
}

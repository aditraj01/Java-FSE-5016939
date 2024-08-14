package com.employee.employeemanagementsystem.dto.primary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeSummary {
    private String name;
    private String email;
    private String departmentName;
}

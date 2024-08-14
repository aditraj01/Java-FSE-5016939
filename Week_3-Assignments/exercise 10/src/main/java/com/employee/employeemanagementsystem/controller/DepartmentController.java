package com.employee.employeemanagementsystem.controller;

import com.employee.employeemanagementsystem.dto.secondary.DepartmentSummary;
import com.employee.employeemanagementsystem.dto.secondary.DepartmentSummaryProjection;
import com.employee.employeemanagementsystem.model.Department;
import com.employee.employeemanagementsystem.repository.secondary.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentRepository.save(department);
        return ResponseEntity.status(201).body(savedDepartment);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setName(departmentDetails.getName());
                    Department updatedDepartment = departmentRepository.save(existingDepartment);
                    return ResponseEntity.ok(updatedDepartment);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/searchByName")
    public ResponseEntity<Department> searchDepartmentByExactName(@RequestParam("name") String name) {
        Department department = departmentRepository.findByName(name);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<DepartmentSummary>> getDepartmentSummaries() {
        List<DepartmentSummary> summaries = departmentRepository.findDepartmentSummaries();
        return ResponseEntity.ok(summaries);
    }

    @GetMapping("/summary-projections")
    public ResponseEntity<List<DepartmentSummaryProjection>> getDepartmentSummaryProjections() {
        List<DepartmentSummaryProjection> projections = departmentRepository.findDepartmentSummaryProjections();
        return ResponseEntity.ok(projections);
    }
}

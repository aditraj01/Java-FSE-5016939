package com.employee.employeemanagementsystem.repository.primary;

import com.employee.employeemanagementsystem.dto.primary.EmployeeSummary;
import com.employee.employeemanagementsystem.dto.primary.EmployeeSummaryProjection;
import com.employee.employeemanagementsystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    @Query(name = "Employee.findByName")
    List<Employee> findByName(@Param("name") String name, Pageable pageable);

    @Query(name = "Employee.findByEmail")
    Employee findByEmail(@Param("email") String email);

    @Query("SELECT new com.employee.employeemanagementsystem.dto.primary.EmployeeSummary(e.name, e.email, d.name) " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeSummary> findEmployeeSummaries();

    @Query("SELECT e.name AS name, e.email AS email, d.name AS departmentName " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeSummaryProjection> findEmployeeSummaryProjections();
}

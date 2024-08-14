package com.employee.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.employee.entity.Employee;
import com.employee.projection.EmployeeProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	// Derived query methods
    List<Employee> findByName(String name);
    
    List<Employee> findByEmailDomain(String domain);
    
    List<Employee> findByDepartmentAndSortByName(String departmentName);
    
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllEmployeeProjections();
    
    List<Employee> findByDepartmentId(Long departmentId);
    
    
    // Custom query to find employees by a fragment of their name
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:nameFragment%")
    List<Employee> findByNameFragment(@Param("nameFragment") String nameFragment);

    // Custom query to count employees in a specific department
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.name = :departmentName")
    long countEmployeesInDepartment(@Param("departmentName") String departmentName);

    // Custom native query to find employees by department ID
    @Query(value = "SELECT * FROM employees e WHERE e.department_id = :departmentId", nativeQuery = true)
    List<Employee> findByDepartmentIdNative(@Param("departmentId") Long departmentId);
    
    // Paginate through employees
    Page<Employee> findAll(Pageable pageable);

    // Paginate and search employees by department name
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

}

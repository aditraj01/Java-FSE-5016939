package com.employee.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.employee.entity.Department;
import com.employee.projection.DepartmentProjection;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	Department findByName(String name);
	
	@Query("SELECT d FROM Department d")
    List<DepartmentProjection> findAllDepartmentProjections();

}

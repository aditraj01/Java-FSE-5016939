package com.employee.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(
        name = "Employee.findByEmailDomain",
        query = "SELECT e FROM Employee e WHERE e.email LIKE CONCAT('%', :domain)"
    ),
    @NamedQuery(
        name = "Employee.findByDepartmentAndSortByName",
        query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName ORDER BY e.name ASC"
    )
})
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    
    public Department getDepartment() {
		return department;
	}
    
    public String getEmail() {
		return email;
	}
    
    public String getName() {
		return name;
	}
    
    public void setDepartment(Department department) {
		this.department = department;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public void setName(String name) {
		this.name = name;
	}
}

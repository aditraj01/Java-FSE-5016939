package com.employee.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name")
})
public class Department extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private Set<Employee> employees;
}

package com.schoolmanagementsystem.repository;

import com.schoolmanagementsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer > {

}

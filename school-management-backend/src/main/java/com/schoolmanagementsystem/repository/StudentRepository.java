package com.schoolmanagementsystem.repository;

import com.schoolmanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer > {

    boolean existsByStudentNumber(String studentNumber);
}

package com.schoolmanagementsystem.repository;

import com.schoolmanagementsystem.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, String >{
}

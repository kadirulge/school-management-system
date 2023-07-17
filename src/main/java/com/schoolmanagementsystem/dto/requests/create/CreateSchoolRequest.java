package com.schoolmanagementsystem.dto.requests.create;

import com.schoolmanagementsystem.entities.Employee;
import com.schoolmanagementsystem.entities.Student;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSchoolRequest {
    private String name;
    private String address;
    private LocalDate dateOfEstablishment;
    private int numberOfClasses;
}


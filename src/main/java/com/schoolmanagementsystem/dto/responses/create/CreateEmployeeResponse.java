package com.schoolmanagementsystem.dto.responses.create;

import com.schoolmanagementsystem.entities.enums.JobTitle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String employeeNumber;
    private JobTitle jobTitle;
    private int schoolId;
}

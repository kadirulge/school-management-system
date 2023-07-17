package com.schoolmanagementsystem.dto.responses.update;

import com.schoolmanagementsystem.entities.enums.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String employeeNumber;
    private boolean isManager;
    private JobTitle jobTitle;
    private int schoolId;
}

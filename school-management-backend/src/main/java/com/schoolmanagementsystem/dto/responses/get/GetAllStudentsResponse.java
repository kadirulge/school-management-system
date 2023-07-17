package com.schoolmanagementsystem.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStudentsResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String studentNumber;
    private int schoolId;
}

package com.schoolmanagementsystem.dto.requests.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;
    @Email
    private String email;
    @NotBlank
    @Size(min = 2, max = 50)
    private String address;
    @NotBlank
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @NotBlank
    @Size(min = 10, max = 10)
    private String studentNumber;
    @Min(1)
    private int schoolId;

}

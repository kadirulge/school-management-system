package com.schoolmanagementsystem.dto.responses.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSchoolResponse {
    private int id;
    private String name;
    private String address;
    private LocalDate dateOfEstablishment;
    private int numberOfClasses;
}

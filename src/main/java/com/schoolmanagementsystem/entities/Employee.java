package com.schoolmanagementsystem.entities;

import com.schoolmanagementsystem.entities.enums.JobTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String employeeNumber;
    private boolean isManager;
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;


    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

}

package com.schoolmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private LocalDate dateOfEstablishment;
    private int numberOfClasses;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Employee> employees;
}

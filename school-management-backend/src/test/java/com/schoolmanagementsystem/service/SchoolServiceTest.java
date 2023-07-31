package com.schoolmanagementsystem.service;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.dto.requests.create.CreateSchoolRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateSchoolRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateSchoolResponse;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllSchoolsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetSchoolResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateSchoolResponse;
import com.schoolmanagementsystem.entities.Employee;
import com.schoolmanagementsystem.entities.School;
import com.schoolmanagementsystem.entities.Student;
import com.schoolmanagementsystem.repository.SchoolRepository;
import com.schoolmanagementsystem.service.rules.SchoolBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {

    @Mock
    private SchoolRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private SchoolBusinessRules rules;

    @InjectMocks
    private SchoolService schoolService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSchool() {
        // Arrange
        CreateSchoolRequest mockRequest = new CreateSchoolRequest();
        mockRequest.setName("Name");
        mockRequest.setAddress("42 Main St");
        mockRequest.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockRequest.setNumberOfClasses(10);
        School mockSchool = new School();
        mockSchool.setId(1);
        mockSchool.setName("Name");
        mockSchool.setAddress("42 Main St");
        mockSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockSchool.setNumberOfClasses(10);
        mockSchool.setStudents(new ArrayList<Student>());
        mockSchool.setEmployees(new ArrayList<Employee>());

        CreateSchoolResponse mockResponse = new CreateSchoolResponse();
        mockResponse.setId(1);
        mockResponse.setName("Name");
        mockResponse.setAddress("42 Main St");
        mockResponse.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockResponse.setNumberOfClasses(10);

        doNothing().when(rules).checkIfSchoolExistsByName(mockRequest.getName());


        when(mapper.map(mockRequest, School.class)).thenReturn(mockSchool);
        when(repository.save(mockSchool)).thenReturn(mockSchool);
        when(mapper.map(mockSchool, CreateSchoolResponse.class)).thenReturn(mockResponse);

        // Act
        CreateSchoolResponse response = schoolService.add(mockRequest);

        // Assert
        assertNotNull(response);
        assertEquals(mockResponse.getId(), response.getId());
        assertEquals(mockResponse.getName(), response.getName());

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsByName(mockRequest.getName());
        verify(mapper, times(1)).map(mockRequest, School.class);
        verify(repository, times(1)).save(mockSchool);
        verify(mapper, times(1)).map(mockSchool, CreateSchoolResponse.class);

    }



}

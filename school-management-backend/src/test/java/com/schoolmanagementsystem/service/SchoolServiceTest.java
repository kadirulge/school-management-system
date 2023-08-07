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
    public void shouldGetAllSchools() {
        // Arrange
        List<School> mockSchools = new ArrayList<>();
        School mockSchool = new School();
        mockSchool.setId(1);
        mockSchool.setName("Name");
        mockSchool.setAddress("42 Main St");
        mockSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockSchool.setNumberOfClasses(10);
        mockSchool.setStudents(new ArrayList<Student>());
        mockSchool.setEmployees(new ArrayList<Employee>());
        mockSchools.add(mockSchool);

        GetAllSchoolsResponse mockResponse = new GetAllSchoolsResponse();
        mockResponse.setId(1);
        mockResponse.setName("Name");
        mockResponse.setAddress("42 Main St");
        mockResponse.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockResponse.setNumberOfClasses(10);

        when(repository.findAll()).thenReturn(mockSchools);
        when(mapper.map(mockSchool, GetAllSchoolsResponse.class)).thenReturn(mockResponse);

        // Act
        List<GetAllSchoolsResponse> response = schoolService.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(mockResponse.getId(), response.get(0).getId());
        assertEquals(mockResponse.getName(), response.get(0).getName());

        // Verify
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).map(mockSchool, GetAllSchoolsResponse.class);
    }

    @Test
    public void shouldGetSchoolById_whenSchoolExistsById() {
        List<School> mockSchools = new ArrayList<>();
        School mockSchool = new School();
        mockSchool.setId(1);
        mockSchool.setName("Name");
        mockSchool.setAddress("42 Main St");
        mockSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockSchool.setNumberOfClasses(10);
        mockSchool.setStudents(new ArrayList<Student>());
        mockSchool.setEmployees(new ArrayList<Employee>());
        mockSchools.add(mockSchool);

        GetSchoolResponse mockResponse = new GetSchoolResponse();
        mockResponse.setId(1);
        mockResponse.setName("Name");
        mockResponse.setAddress("42 Main St");
        mockResponse.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockResponse.setNumberOfClasses(10);

        when(repository.findById(1)).thenReturn(Optional.of(mockSchool));
        when(mapper.map(mockSchool, GetSchoolResponse.class)).thenReturn(mockResponse);

        // Act
        GetSchoolResponse response = schoolService.getById(1);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(mockResponse, response);

        // Verify
        //verify(rules, times(1)).checkIfSchoolExistsById(1);
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).map(mockSchool, GetSchoolResponse.class);

    }

    @Test
    public void shouldThrowException_whenSchoolDoesNotExistById() {
        List<School> mockSchools = new ArrayList<>();
        School mockSchool = new School();
        mockSchool.setId(1);
        mockSchool.setName("Name");
        mockSchool.setAddress("42 Main St");
        mockSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockSchool.setNumberOfClasses(10);
        mockSchool.setStudents(new ArrayList<Student>());
        mockSchool.setEmployees(new ArrayList<Employee>());
        mockSchools.add(mockSchool);

        GetSchoolResponse mockResponse = new GetSchoolResponse();
        mockResponse.setId(1);
        mockResponse.setName("Name");
        mockResponse.setAddress("42 Main St");
        mockResponse.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockResponse.setNumberOfClasses(10);


        //doThrow(new BusinessException(Messages.School.NotExists)).when(rules).checkIfSchoolExistsById(2);

        // Act & Assert
        assertThrows(BusinessException.class, () -> schoolService.getById(2));

        // Verify
        //verify(rules, times(1)).checkIfSchoolExistsById(2);
        verify(repository, times(1)).findById(2);
        verify(mapper, times(0)).map(mockSchool, GetSchoolResponse.class);
    }


    @Test
    public void shouldAddSchool_whenSchoolDoesNotExistsByName() {
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
        assertEquals(mockResponse, response);

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsByName(mockRequest.getName());
        verify(mapper, times(1)).map(mockRequest, School.class);
        verify(repository, times(1)).save(mockSchool);
        verify(mapper, times(1)).map(mockSchool, CreateSchoolResponse.class);

    }

    @Test
    public void shouldThrowExceptionWhenAddingSchoolWithExistingName() {
        // Arrange
        List<School> mockSchools = new ArrayList<>();
        School mockSchool = new School();
        mockSchool.setId(1);
        mockSchool.setName("Name");
        mockSchool.setAddress("42 Main St");
        mockSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockSchool.setNumberOfClasses(10);
        mockSchool.setStudents(new ArrayList<Student>());
        mockSchool.setEmployees(new ArrayList<Employee>());
        mockSchools.add(mockSchool);

        CreateSchoolRequest mockRequest = new CreateSchoolRequest();
        mockRequest.setName("Name");
        mockRequest.setAddress("42 Main St");
        mockRequest.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        mockRequest.setNumberOfClasses(10);


        doThrow(new BusinessException(Messages.School.Exists)).when(rules).checkIfSchoolExistsByName(mockRequest.getName());

        // Act & Assert
        assertThrows(BusinessException.class, () -> schoolService.add(mockRequest));

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsByName(mockRequest.getName());
    }

    @Test
    public void testUpdateSchoolWhenSchoolExistById() {
        // Arrange
        UpdateSchoolRequest mockRequest = new UpdateSchoolRequest();
        mockRequest.setName("New Name");
        mockRequest.setAddress("New Address");
        mockRequest.setDateOfEstablishment(LocalDate.of(2022, 1, 1));
        mockRequest.setNumberOfClasses(12);

        School existingSchool = new School();
        existingSchool.setId(1);
        existingSchool.setName("Old Name");
        existingSchool.setAddress("Old Address");
        existingSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        existingSchool.setNumberOfClasses(10);

        UpdateSchoolResponse expectedResponse = new UpdateSchoolResponse();
        expectedResponse.setId(1);
        expectedResponse.setName("New Name");
        expectedResponse.setAddress("New Address");
        expectedResponse.setDateOfEstablishment(LocalDate.of(2022, 1, 1));
        expectedResponse.setNumberOfClasses(12);

        // Mock the behavior to check if the school exists by id
        doNothing().when(rules).checkIfSchoolExistsById(1);
        // Mock the mapper to map the request and the updated school
        when(mapper.map(mockRequest, School.class)).thenReturn(existingSchool);
        existingSchool.setId(1);
        // Mock the behavior to save the updated school
        when(repository.save(any(School.class))).thenReturn(existingSchool);
        when(mapper.map(existingSchool, UpdateSchoolResponse.class)).thenReturn(expectedResponse);

        // Act
        UpdateSchoolResponse response = schoolService.update(1, mockRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsById(1);
        verify(repository, times(1)).save(any(School.class));
        verify(mapper, times(1)).map(mockRequest, School.class);
        verify(mapper, times(1)).map(existingSchool, UpdateSchoolResponse.class);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingSchoolWithMotExistingId() {
        // Arrange
        int nonExistingSchoolId = 100;
        UpdateSchoolRequest mockRequest = new UpdateSchoolRequest();
        mockRequest.setName("New Name");
        mockRequest.setAddress("New Address");
        mockRequest.setDateOfEstablishment(LocalDate.of(2022, 1, 1));
        mockRequest.setNumberOfClasses(12);

        doThrow(new BusinessException(Messages.School.NotExists)).when(rules).checkIfSchoolExistsById(nonExistingSchoolId);

        // Act & Assert
        assertThrows(BusinessException.class, () -> schoolService.update(nonExistingSchoolId, mockRequest));

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsById(nonExistingSchoolId);
    }

    @Test
    public void testDeleteSchoolById() {
        // Arrange
        School existingSchool = new School();
        existingSchool.setId(1);
        existingSchool.setName("Existing School");
        existingSchool.setAddress("42 Main St");
        existingSchool.setDateOfEstablishment(LocalDate.of(2021, 1, 1));
        existingSchool.setNumberOfClasses(10);

        // Mock the behavior to check if the school exists by id
        doNothing().when(rules).checkIfSchoolExistsById(1);
        // Mock the behavior to delete the school by id
        doNothing().when(repository).deleteById(1);

        // Act
        schoolService.delete(1);

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsById(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteNonExistingSchoolById() {
        // Arrange
        int nonExistingSchoolId = 100;

        // Mock the behavior to check if the school exists by id and throw an exception
        doThrow(new BusinessException(Messages.School.NotExists)).when(rules).checkIfSchoolExistsById(nonExistingSchoolId);

        // Act & Assert
        assertThrows(BusinessException.class, () -> schoolService.delete(nonExistingSchoolId));

        // Verify
        verify(rules, times(1)).checkIfSchoolExistsById(nonExistingSchoolId);
        // Since the delete method should not be called for a non-existing school, we don't verify it here
    }

}


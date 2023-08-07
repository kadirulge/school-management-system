package com.schoolmanagementsystem.service;


import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.common.events.StudentCreatedEvent;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.dto.requests.create.CreateStudentRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateStudentRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllStudentsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetStudentResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateStudentResponse;
import com.schoolmanagementsystem.entities.School;
import com.schoolmanagementsystem.entities.Student;
import com.schoolmanagementsystem.repository.StudentRepository;
import com.schoolmanagementsystem.service.rabbitmq.producer.RabbitMqProducer;
import com.schoolmanagementsystem.service.rules.StudentBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private StudentBusinessRules rules;

    @Mock
    private  RabbitMqProducer producer;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldGetAllStudents() {
        // Arrange
        List<Student> mockStudents = new ArrayList<>();
        Student mockStudent = new Student();
        mockStudent.setId(1);
        mockStudent.setFirstName("First Name");
        mockStudent.setLastName("Last Name");
        mockStudent.setEmail("student@gmail.com");
        mockStudent.setAddress("42 Main St");
        mockStudent.setPhoneNumber("1234567890");
        mockStudent.setStudentNumber("1234567890");
        mockStudent.setSchool(new School());

        mockStudents.add(mockStudent);

        GetAllStudentsResponse mockResponse = new GetAllStudentsResponse();
        mockResponse.setId(1);
        mockResponse.setFirstName("First Name");
        mockResponse.setLastName("Last Name");
        mockResponse.setEmail("student@gmail.com");
        mockResponse.setAddress("42 Main St");
        mockResponse.setPhoneNumber("1234567890");
        mockResponse.setStudentNumber("1234567890");

        when(repository.findAll()).thenReturn(mockStudents);
        when(mapper.map(mockStudent, GetAllStudentsResponse.class)).thenReturn(mockResponse);

        // Act
        List<GetAllStudentsResponse> response = studentService.getAll();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(mockResponse.getId(), response.get(0).getId());
        assertEquals(mockResponse.getFirstName(), response.get(0).getFirstName());

        // Verify
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).map(mockStudent, GetAllStudentsResponse.class);
    }

    @Test
    public void shouldGetStudentById_whenStudentExistsById() {
        List<Student> mockStudents = new ArrayList<>();
        Student mockStudent = new Student();
        mockStudent.setId(1);
        mockStudent.setFirstName("First Name");
        mockStudent.setLastName("Last Name");
        mockStudent.setEmail("student@gmail.com");
        mockStudent.setAddress("42 Main St");
        mockStudent.setPhoneNumber("1234567890");
        mockStudent.setStudentNumber("1234567890");
        mockStudent.setSchool(new School());

        mockStudents.add(mockStudent);

        GetStudentResponse mockResponse = new GetStudentResponse();
        mockResponse.setId(1);
        mockResponse.setFirstName("First Name");
        mockResponse.setLastName("Last Name");
        mockResponse.setEmail("student@gmail.com");
        mockResponse.setAddress("42 Main St");
        mockResponse.setPhoneNumber("1234567890");
        mockResponse.setStudentNumber("1234567890");

        when(repository.findById(1)).thenReturn(Optional.of(mockStudent));
        when(mapper.map(mockStudent, GetStudentResponse.class)).thenReturn(mockResponse);

        // Act
        GetStudentResponse response = studentService.getById(1);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(mockResponse, response);

        // Verify
        //verify(rules, times(1)).checkIfStudentExistsById(1);
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).map(mockStudent, GetStudentResponse.class);

    }

    @Test
    public void shouldThrowException_whenStudentDoesNotExistById() {
        List<Student> mockStudents = new ArrayList<>();
        Student mockStudent = new Student();
        mockStudent.setId(1);
        mockStudent.setFirstName("First Name");
        mockStudent.setLastName("Last Name");
        mockStudent.setEmail("student@gmail.com");
        mockStudent.setAddress("42 Main St");
        mockStudent.setPhoneNumber("1234567890");
        mockStudent.setStudentNumber("1234567890");
        mockStudent.setSchool(new School());

        mockStudents.add(mockStudent);

        GetStudentResponse mockResponse = new GetStudentResponse();
        mockResponse.setId(1);
        mockResponse.setFirstName("First Name");
        mockResponse.setLastName("Last Name");
        mockResponse.setEmail("student@gmail.com");
        mockResponse.setAddress("42 Main St");
        mockResponse.setPhoneNumber("1234567890");
        mockResponse.setStudentNumber("1234567890");

        //doThrow(new BusinessException(Messages.Student.NotExists)).when(rules).checkIfStudentExistsById(2);

        // Act & Assert
        assertThrows(BusinessException.class, () -> studentService.getById(2));

        // Verify
        //verify(rules, times(1)).checkIfStudentExistsById(2);
        verify(repository, times(1)).findById(2);
        verify(mapper, times(0)).map(mockStudent, GetStudentResponse.class);
    }


    @Test
    public void shouldAddStudent_whenStudentDoesNotExistsByName() {
        // Arrange
        CreateStudentRequest mockRequest = new CreateStudentRequest();
        mockRequest.setFirstName("First Name");
        mockRequest.setLastName("Last Name");
        mockRequest.setEmail("student@gmail.com");
        mockRequest.setAddress("42 Main St");
        mockRequest.setPhoneNumber("1234567890");
        mockRequest.setStudentNumber("1234567890");


        Student mockStudent = new Student();
        mockStudent.setId(1);
        mockStudent.setFirstName("First Name");
        mockStudent.setLastName("Last Name");
        mockStudent.setEmail("student@gmail.com");
        mockStudent.setAddress("42 Main St");
        mockStudent.setPhoneNumber("1234567890");
        mockStudent.setStudentNumber("1234567890");
        mockStudent.setSchool(new School());

        CreateStudentResponse mockResponse = new CreateStudentResponse();
        mockResponse.setId(1);
        mockResponse.setFirstName("First Name");
        mockResponse.setLastName("Last Name");
        mockResponse.setEmail("student@gmail.com");
        mockResponse.setAddress("42 Main St");
        mockResponse.setPhoneNumber("1234567890");
        mockResponse.setStudentNumber("1234567890");

        doNothing().when(rules).checkIfStudentExistsByStudentNumber(mockRequest.getStudentNumber());


        when(mapper.map(mockRequest, Student.class)).thenReturn(mockStudent);
        when(repository.save(mockStudent)).thenReturn(mockStudent);

        sendRabbitMqStudentCreatedEvent(mockRequest.getSchoolId());
        when(mapper.map(mockStudent, CreateStudentResponse.class)).thenReturn(mockResponse);


        // Act
        CreateStudentResponse response = studentService.add(mockRequest);

        // Assert
        assertNotNull(response);
        assertEquals(mockResponse, response);

        // Verify
        verify(rules, times(1)).checkIfStudentExistsByStudentNumber(mockRequest.getStudentNumber());
        verify(mapper, times(1)).map(mockRequest, Student.class);
        verify(repository, times(1)).save(mockStudent);
        verify(mapper, times(1)).map(mockStudent, CreateStudentResponse.class);

    }

    @Test
    public void shouldThrowExceptionWhenAddingStudentWithExistingName() {
        // Arrange
        List<Student> mockStudents = new ArrayList<>();
        Student mockStudent = new Student();
        mockStudent.setId(1);
        mockStudent.setFirstName("First Name");
        mockStudent.setLastName("Last Name");
        mockStudent.setEmail("student@gmail.com");
        mockStudent.setAddress("42 Main St");
        mockStudent.setPhoneNumber("1234567890");
        mockStudent.setStudentNumber("1234567890");
        mockStudent.setSchool(new School());

        CreateStudentRequest mockRequest = new CreateStudentRequest();
        mockRequest.setFirstName("First Name");
        mockRequest.setLastName("Last Name");
        mockRequest.setEmail("student@gmail.com");
        mockRequest.setAddress("42 Main St");
        mockRequest.setPhoneNumber("1234567890");
        mockRequest.setStudentNumber("1234567890");


        doThrow(new BusinessException(Messages.Student.Exists)).when(rules).checkIfStudentExistsByStudentNumber(mockRequest.getStudentNumber());

        // Act & Assert
        assertThrows(BusinessException.class, () -> studentService.add(mockRequest));

        // Verify
        verify(rules, times(1)).checkIfStudentExistsByStudentNumber(mockRequest.getStudentNumber());
    }

    @Test
    public void testUpdateStudentWhenStudentExistById() {
        // Arrange
        UpdateStudentRequest mockRequest = new UpdateStudentRequest();
        mockRequest.setFirstName("First Name");
        mockRequest.setLastName("Last Name");
        mockRequest.setEmail("newmail@gmail.com");
        mockRequest.setAddress("42 Main St");
        mockRequest.setPhoneNumber("1234567890");
        mockRequest.setStudentNumber("1234567890");

        Student existingStudent = new Student();
        existingStudent.setId(1);
        existingStudent.setFirstName("First Name");
        existingStudent.setLastName("Last Name");
        existingStudent.setEmail("student@gmail.com");
        existingStudent.setAddress("42 Main St");
        existingStudent.setPhoneNumber("1234567890");
        existingStudent.setStudentNumber("1234567890");
        existingStudent.setSchool(new School());

        UpdateStudentResponse expectedResponse = new UpdateStudentResponse();
        expectedResponse.setId(1);
        expectedResponse.setFirstName("First Name");
        expectedResponse.setLastName("Last Name");
        expectedResponse.setEmail("newmail@gmail.com");
        expectedResponse.setAddress("42 Main St");
        expectedResponse.setPhoneNumber("1234567890");
        expectedResponse.setStudentNumber("1234567890");

        // Mock the behavior to check if the student exists by id
        doNothing().when(rules).checkIfStudentExistsById(1);
        // Mock the mapper to map the request and the updated student
        when(mapper.map(mockRequest, Student.class)).thenReturn(existingStudent);
        existingStudent.setId(1);
        // Mock the behavior to save the updated student
        when(repository.save(any(Student.class))).thenReturn(existingStudent);
        when(mapper.map(existingStudent, UpdateStudentResponse.class)).thenReturn(expectedResponse);

        // Act
        UpdateStudentResponse response = studentService.update(1, mockRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        // Verify
        verify(rules, times(1)).checkIfStudentExistsById(1);
        verify(repository, times(1)).save(any(Student.class));
        verify(mapper, times(1)).map(mockRequest, Student.class);
        verify(mapper, times(1)).map(existingStudent, UpdateStudentResponse.class);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingStudentWithMotExistingId() {
        // Arrange
        int nonExistingStudentId = 100;
        UpdateStudentRequest mockRequest = new UpdateStudentRequest();
        mockRequest.setFirstName("First Name");
        mockRequest.setLastName("Last Name");
        mockRequest.setEmail("student@gmail.com");
        mockRequest.setAddress("42 Main St");
        mockRequest.setPhoneNumber("1234567890");
        mockRequest.setStudentNumber("1234567890");

        doThrow(new BusinessException(Messages.Student.NotExists)).when(rules).checkIfStudentExistsById(nonExistingStudentId);

        // Act & Assert
        assertThrows(BusinessException.class, () -> studentService.update(nonExistingStudentId, mockRequest));

        // Verify
        verify(rules, times(1)).checkIfStudentExistsById(nonExistingStudentId);
    }

    @Test
    public void testDeleteStudentById() {
        // Arrange
        Student existingStudent = new Student();
        existingStudent.setId(1);
        existingStudent.setFirstName("First Name");
        existingStudent.setLastName("Last Name");
        existingStudent.setEmail("student@gmail.com");
        existingStudent.setAddress("42 Main St");
        existingStudent.setPhoneNumber("1234567890");
        existingStudent.setStudentNumber("1234567890");
        existingStudent.setSchool(new School());

        // Mock the behavior to check if the student exists by id
        doNothing().when(rules).checkIfStudentExistsById(1);
        // Mock the behavior to delete the student by id
        doNothing().when(repository).deleteById(1);

        // Act
        studentService.delete(1);

        // Verify
        verify(rules, times(1)).checkIfStudentExistsById(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteNonExistingStudentById() {
        // Arrange
        int nonExistingStudentId = 100;

        // Mock the behavior to check if the student exists by id and throw an exception
        doThrow(new BusinessException(Messages.Student.NotExists)).when(rules).checkIfStudentExistsById(nonExistingStudentId);

        // Act & Assert
        assertThrows(BusinessException.class, () -> studentService.delete(nonExistingStudentId));

        // Verify
        verify(rules, times(1)).checkIfStudentExistsById(nonExistingStudentId);
    }


    private void sendRabbitMqStudentCreatedEvent(int schoolId)
    {
        producer.sendMessage(new StudentCreatedEvent(schoolId), "student-created");
    }
}
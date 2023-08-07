package com.schoolmanagementsystem.service;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.dto.requests.create.CreateEmployeeRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateEmployeeRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllEmployeesResponse;
import com.schoolmanagementsystem.dto.responses.get.GetEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateEmployeeResponse;
import com.schoolmanagementsystem.entities.School;
import com.schoolmanagementsystem.entities.Employee;
import com.schoolmanagementsystem.entities.enums.JobTitle;
import com.schoolmanagementsystem.repository.EmployeeRepository;
import com.schoolmanagementsystem.service.rules.EmployeeBusinessRules;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest{
        @Mock
        private EmployeeRepository repository;

        @Mock
        private ModelMapper mapper;

        @Mock
        private EmployeeBusinessRules rules;

        @InjectMocks
        private EmployeeService employeeService;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }


        @Test
        public void shouldGetAllEmployees() {
            // Arrange
            List<Employee> mockEmployees = new ArrayList<>();
            Employee mockEmployee = new Employee();
            mockEmployee.setId(1);
            mockEmployee.setFirstName("First Name");
            mockEmployee.setLastName("Last Name");
            mockEmployee.setAddress("42 Main St");
            mockEmployee.setPhoneNumber("1234567890");
            mockEmployee.setEmployeeNumber("1234567890");
            mockEmployee.setManager(true);
            mockEmployee.setJobTitle(JobTitle.COOK);
            mockEmployee.setSchool(new School());

            mockEmployees.add(mockEmployee);

            GetAllEmployeesResponse mockResponse = new GetAllEmployeesResponse();
            mockResponse.setId(1);
            mockResponse.setFirstName("First Name");
            mockResponse.setLastName("Last Name");
            mockResponse.setAddress("42 Main St");
            mockResponse.setPhoneNumber("1234567890");
            mockResponse.setEmployeeNumber("1234567890");
            mockResponse.setManager(true);
            mockResponse.setJobTitle(JobTitle.COOK);

            when(repository.findAll()).thenReturn(mockEmployees);
            when(mapper.map(mockEmployee, GetAllEmployeesResponse.class)).thenReturn(mockResponse);

            // Act
            List<GetAllEmployeesResponse> response = employeeService.getAll();

            // Assert
            assertNotNull(response);
            assertEquals(1, response.size());
            assertEquals(mockResponse.getId(), response.get(0).getId());
            assertEquals(mockResponse.getFirstName(), response.get(0).getFirstName());

            // Verify
            verify(repository, times(1)).findAll();
            verify(mapper, times(1)).map(mockEmployee, GetAllEmployeesResponse.class);
        }

        @Test
        public void shouldGetEmployeeById_whenEmployeeExistsById() {
            List<Employee> mockEmployees = new ArrayList<>();
            Employee mockEmployee = new Employee();
            mockEmployee.setId(1);
            mockEmployee.setFirstName("First Name");
            mockEmployee.setLastName("Last Name");
            mockEmployee.setAddress("42 Main St");
            mockEmployee.setPhoneNumber("1234567890");
            mockEmployee.setEmployeeNumber("1234567890");
            mockEmployee.setManager(true);
            mockEmployee.setJobTitle(JobTitle.COOK);
            mockEmployee.setSchool(new School());

            mockEmployees.add(mockEmployee);

            GetEmployeeResponse mockResponse = new GetEmployeeResponse();
            mockResponse.setId(1);
            mockResponse.setFirstName("First Name");
            mockResponse.setLastName("Last Name");
            mockResponse.setAddress("42 Main St");
            mockResponse.setPhoneNumber("1234567890");
            mockResponse.setEmployeeNumber("1234567890");
            mockResponse.setManager(true);
            mockResponse.setJobTitle(JobTitle.COOK);

            when(repository.findById(1)).thenReturn(Optional.of(mockEmployee));
            when(mapper.map(mockEmployee, GetEmployeeResponse.class)).thenReturn(mockResponse);

            // Act
            GetEmployeeResponse response = employeeService.getById(1);

            // Assert
            assertNotNull(response);
            assertEquals(1, response.getId());
            assertEquals(mockResponse, response);

            // Verify
            //verify(rules, times(1)).checkIfEmployeeExistsById(1);
            verify(repository, times(1)).findById(1);
            verify(mapper, times(1)).map(mockEmployee, GetEmployeeResponse.class);

        }

        @Test
        public void shouldThrowException_whenEmployeeDoesNotExistById() {
            List<Employee> mockEmployees = new ArrayList<>();
            Employee mockEmployee = new Employee();
            mockEmployee.setId(1);
            mockEmployee.setFirstName("First Name");
            mockEmployee.setLastName("Last Name");
            mockEmployee.setAddress("42 Main St");
            mockEmployee.setPhoneNumber("1234567890");
            mockEmployee.setEmployeeNumber("1234567890");
            mockEmployee.setManager(true);
            mockEmployee.setJobTitle(JobTitle.COOK);
            mockEmployee.setSchool(new School());

            mockEmployees.add(mockEmployee);

            GetEmployeeResponse mockResponse = new GetEmployeeResponse();
            mockResponse.setId(1);
            mockResponse.setFirstName("First Name");
            mockResponse.setLastName("Last Name");
            mockResponse.setAddress("42 Main St");
            mockResponse.setPhoneNumber("1234567890");
            mockResponse.setEmployeeNumber("1234567890");
            mockResponse.setManager(true);
            mockResponse.setJobTitle(JobTitle.COOK);

            //doThrow(new BusinessException(Messages.Employee.NotExists)).when(rules).checkIfEmployeeExistsById(2);

            // Act & Assert
            assertThrows(BusinessException.class, () -> employeeService.getById(2));

            // Verify
            //verify(rules, times(1)).checkIfEmployeeExistsById(2);
            verify(repository, times(1)).findById(2);
            verify(mapper, times(0)).map(mockEmployee, GetEmployeeResponse.class);
        }


        @Test
        public void shouldAddEmployee_whenEmployeeDoesNotExistsByName() {
            // Arrange
            CreateEmployeeRequest mockRequest = new CreateEmployeeRequest();
            mockRequest.setFirstName("First Name");
            mockRequest.setLastName("Last Name");
            mockRequest.setAddress("42 Main St");
            mockRequest.setPhoneNumber("1234567890");
            mockRequest.setEmployeeNumber("1234567890");
            mockRequest.setManager(true);
            mockRequest.setJobTitle(JobTitle.COOK);


            Employee mockEmployee = new Employee();
            mockEmployee.setId(1);
            mockEmployee.setFirstName("First Name");
            mockEmployee.setLastName("Last Name");
            mockEmployee.setAddress("42 Main St");
            mockEmployee.setPhoneNumber("1234567890");
            mockEmployee.setEmployeeNumber("1234567890");
            mockEmployee.setManager(true);
            mockEmployee.setJobTitle(JobTitle.COOK);
            mockEmployee.setSchool(new School());

            CreateEmployeeResponse mockResponse = new CreateEmployeeResponse();
            mockResponse.setId(1);
            mockResponse.setFirstName("First Name");
            mockResponse.setLastName("Last Name");
            mockResponse.setAddress("42 Main St");
            mockResponse.setPhoneNumber("1234567890");
            mockResponse.setEmployeeNumber("1234567890");
            mockResponse.setManager(true);
            mockResponse.setJobTitle(JobTitle.COOK);

            doNothing().when(rules).checkIfEmployeeExistsByEmployeeNumber(mockRequest.getEmployeeNumber());


            when(mapper.map(mockRequest, Employee.class)).thenReturn(mockEmployee);
            when(repository.save(mockEmployee)).thenReturn(mockEmployee);
            when(mapper.map(mockEmployee, CreateEmployeeResponse.class)).thenReturn(mockResponse);

            // Act
            CreateEmployeeResponse response = employeeService.add(mockRequest);

            // Assert
            assertNotNull(response);
            assertEquals(mockResponse, response);

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsByEmployeeNumber(mockRequest.getEmployeeNumber());
            verify(mapper, times(1)).map(mockRequest, Employee.class);
            verify(repository, times(1)).save(mockEmployee);
            verify(mapper, times(1)).map(mockEmployee, CreateEmployeeResponse.class);

        }

        @Test
        public void shouldThrowExceptionWhenAddingEmployeeWithExistingName() {
            // Arrange
            List<Employee> mockEmployees = new ArrayList<>();
            Employee mockEmployee = new Employee();
            mockEmployee.setId(1);
            mockEmployee.setFirstName("First Name");
            mockEmployee.setLastName("Last Name");
            mockEmployee.setAddress("42 Main St");
            mockEmployee.setPhoneNumber("1234567890");
            mockEmployee.setEmployeeNumber("1234567890");
            mockEmployee.setManager(true);
            mockEmployee.setJobTitle(JobTitle.COOK);
            mockEmployee.setSchool(new School());

            CreateEmployeeRequest mockRequest = new CreateEmployeeRequest();
            mockRequest.setFirstName("First Name");
            mockRequest.setLastName("Last Name");
            mockRequest.setAddress("42 Main St");
            mockRequest.setPhoneNumber("1234567890");
            mockRequest.setEmployeeNumber("1234567890");
            mockRequest.setManager(true);
            mockRequest.setJobTitle(JobTitle.COOK);


            doThrow(new BusinessException(Messages.Employee.Exists)).when(rules).checkIfEmployeeExistsByEmployeeNumber(mockRequest.getEmployeeNumber());

            // Act & Assert
            assertThrows(BusinessException.class, () -> employeeService.add(mockRequest));

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsByEmployeeNumber(mockRequest.getEmployeeNumber());
        }

        @Test
        public void testUpdateEmployeeWhenEmployeeExistById() {
            // Arrange
            UpdateEmployeeRequest mockRequest = new UpdateEmployeeRequest();
            mockRequest.setFirstName("First Name");
            mockRequest.setLastName("Last Name");
            mockRequest.setAddress("new 42 Main St");
            mockRequest.setPhoneNumber("1234567890");
            mockRequest.setEmployeeNumber("1234567890");
            mockRequest.setManager(true);
            mockRequest.setJobTitle(JobTitle.COOK);

            Employee existingEmployee = new Employee();
            existingEmployee.setId(1);
            existingEmployee.setFirstName("First Name");
            existingEmployee.setLastName("Last Name");
            existingEmployee.setAddress("42 Main St");
            existingEmployee.setPhoneNumber("1234567890");
            existingEmployee.setEmployeeNumber("1234567890");
            existingEmployee.setManager(true);
            existingEmployee.setJobTitle(JobTitle.COOK);
            existingEmployee.setSchool(new School());

            UpdateEmployeeResponse expectedResponse = new UpdateEmployeeResponse();
            expectedResponse.setId(1);
            expectedResponse.setFirstName("First Name");
            expectedResponse.setLastName("Last Name");
            expectedResponse.setAddress("new 42 Main St");
            expectedResponse.setPhoneNumber("1234567890");
            expectedResponse.setEmployeeNumber("1234567890");
            expectedResponse.setManager(true);
            expectedResponse.setJobTitle(JobTitle.COOK);

            // Mock the behavior to check if the employee exists by id
            doNothing().when(rules).checkIfEmployeeExistsById(1);
            // Mock the mapper to map the request and the updated employee
            when(mapper.map(mockRequest, Employee.class)).thenReturn(existingEmployee);
            existingEmployee.setId(1);
            // Mock the behavior to save the updated employee
            when(repository.save(any(Employee.class))).thenReturn(existingEmployee);
            when(mapper.map(existingEmployee, UpdateEmployeeResponse.class)).thenReturn(expectedResponse);

            // Act
            UpdateEmployeeResponse response = employeeService.update(1, mockRequest);

            // Assert
            assertNotNull(response);
            assertEquals(expectedResponse, response);

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsById(1);
            verify(repository, times(1)).save(any(Employee.class));
            verify(mapper, times(1)).map(mockRequest, Employee.class);
            verify(mapper, times(1)).map(existingEmployee, UpdateEmployeeResponse.class);
        }

        @Test
        public void shouldThrowExceptionWhenUpdatingEmployeeWithMotExistingId() {
            // Arrange
            int nonExistingEmployeeId = 100;
            UpdateEmployeeRequest mockRequest = new UpdateEmployeeRequest();
            mockRequest.setFirstName("First Name");
            mockRequest.setLastName("Last Name");
            mockRequest.setAddress("new 42 Main St");
            mockRequest.setPhoneNumber("1234567890");
            mockRequest.setEmployeeNumber("1234567890");
            mockRequest.setManager(true);
            mockRequest.setJobTitle(JobTitle.COOK);

            doThrow(new BusinessException(Messages.Employee.NotExists)).when(rules).checkIfEmployeeExistsById(nonExistingEmployeeId);

            // Act & Assert
            assertThrows(BusinessException.class, () -> employeeService.update(nonExistingEmployeeId, mockRequest));

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsById(nonExistingEmployeeId);
        }

        @Test
        public void testDeleteEmployeeById() {
            // Arrange
            Employee existingEmployee = new Employee();
            existingEmployee.setId(1);
            existingEmployee.setFirstName("First Name");
            existingEmployee.setLastName("Last Name");
            existingEmployee.setAddress("42 Main St");
            existingEmployee.setPhoneNumber("1234567890");
            existingEmployee.setEmployeeNumber("1234567890");
            existingEmployee.setManager(true);
            existingEmployee.setJobTitle(JobTitle.COOK);
            existingEmployee.setSchool(new School());

            // Mock the behavior to check if the employee exists by id
            doNothing().when(rules).checkIfEmployeeExistsById(1);
            // Mock the behavior to delete the employee by id
            doNothing().when(repository).deleteById(1);

            // Act
            employeeService.delete(1);

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsById(1);
            verify(repository, times(1)).deleteById(1);
        }

        @Test
        public void testDeleteNonExistingEmployeeById() {
            // Arrange
            int nonExistingEmployeeId = 100;

            // Mock the behavior to check if the employee exists by id and throw an exception
            doThrow(new BusinessException(Messages.Employee.NotExists)).when(rules).checkIfEmployeeExistsById(nonExistingEmployeeId);

            // Act & Assert
            assertThrows(BusinessException.class, () -> employeeService.delete(nonExistingEmployeeId));

            // Verify
            verify(rules, times(1)).checkIfEmployeeExistsById(nonExistingEmployeeId);
            // Since the delete method should not be called for a non-existing employee, we don't verify it here
        }
}
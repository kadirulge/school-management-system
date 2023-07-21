package com.schoolmanagementsystem.service;

import com.schoolmanagementsystem.dto.requests.create.CreateEmployeeRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateEmployeeRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllEmployeesResponse;
import com.schoolmanagementsystem.dto.responses.get.GetEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateEmployeeResponse;
import com.schoolmanagementsystem.entities.Employee;
import com.schoolmanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final ModelMapper mapper;


    public List<GetAllEmployeesResponse> getAll() {
        final List<Employee> employees = repository.findAll();
        final List<GetAllEmployeesResponse> response = employees
                .stream()
                .map(employee -> mapper.map(employee, GetAllEmployeesResponse.class))
                .toList();

        return response;
    }


    public GetEmployeeResponse getById(int id) {
        final Employee employee = repository.findById(id).orElseThrow();
        final GetEmployeeResponse response = mapper.map(employee, GetEmployeeResponse.class);

        return response;
    }


    public CreateEmployeeResponse add(CreateEmployeeRequest request) {
        final Employee employee = mapper.map(request, Employee.class);
        employee.setId(0);
        repository.save(employee);
        final CreateEmployeeResponse response = mapper.map(employee, CreateEmployeeResponse.class);

        return response;
    }


    public UpdateEmployeeResponse update(int id, UpdateEmployeeRequest request) {
        final Employee employee = mapper.map(request, Employee.class);
        employee.setId(id);
        repository.save(employee);
        final UpdateEmployeeResponse response = mapper.map(employee, UpdateEmployeeResponse.class);

        return response;
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}

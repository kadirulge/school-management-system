package com.schoolmanagementsystem.service.concretes;

import com.schoolmanagementsystem.dto.requests.create.CreateEmployeeRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateEmployeeRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllEmployeesResponse;
import com.schoolmanagementsystem.dto.responses.get.GetEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateEmployeeResponse;
import com.schoolmanagementsystem.entities.Employee;
import com.schoolmanagementsystem.repository.EmployeeRepository;
import com.schoolmanagementsystem.service.abstracts.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllEmployeesResponse> getAll() {
        List<Employee> employees = repository.findAll();
        List<GetAllEmployeesResponse> response = employees
                .stream()
                .map(employee -> mapper.map(employee, GetAllEmployeesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetEmployeeResponse getById(int id) {
        Employee employee = repository.findById(id).orElseThrow();
        GetEmployeeResponse response = mapper.map(employee, GetEmployeeResponse.class);

        return response;
    }

    @Override
    public CreateEmployeeResponse add(CreateEmployeeRequest request) {
        Employee employee = mapper.map(request, Employee.class);
        employee.setId(0);
        repository.save(employee);
        CreateEmployeeResponse response = mapper.map(employee, CreateEmployeeResponse.class);

        return response;
    }

    @Override
    public UpdateEmployeeResponse update(int id, UpdateEmployeeRequest request) {
        Employee employee = mapper.map(request, Employee.class);
        employee.setId(id);
        repository.save(employee);
        UpdateEmployeeResponse response = mapper.map(employee, UpdateEmployeeResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}

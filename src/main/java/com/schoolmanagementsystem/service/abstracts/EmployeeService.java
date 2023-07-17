package com.schoolmanagementsystem.service.abstracts;

import com.schoolmanagementsystem.dto.requests.create.CreateEmployeeRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateEmployeeRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllEmployeesResponse;
import com.schoolmanagementsystem.dto.responses.get.GetEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateEmployeeResponse;

import java.util.List;


public interface EmployeeService {

    List<GetAllEmployeesResponse> getAll();
    GetEmployeeResponse getById(int id);
    CreateEmployeeResponse add(CreateEmployeeRequest request);
    UpdateEmployeeResponse update(int id, UpdateEmployeeRequest request);
    void delete(int id);
}

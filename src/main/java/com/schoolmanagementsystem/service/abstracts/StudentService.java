package com.schoolmanagementsystem.service.abstracts;

import com.schoolmanagementsystem.dto.requests.create.CreateStudentRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateStudentRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllStudentsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetStudentResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateStudentResponse;

import java.util.List;


public interface StudentService {
    List<GetAllStudentsResponse> getAll();
    GetStudentResponse getById(int id);
    CreateStudentResponse add(CreateStudentRequest request);
    UpdateStudentResponse update(int id, UpdateStudentRequest request);
    void delete(int id);
}

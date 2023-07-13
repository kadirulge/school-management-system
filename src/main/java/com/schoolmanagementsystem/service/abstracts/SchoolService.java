package com.schoolmanagementsystem.service.abstracts;

import com.schoolmanagementsystem.dto.requests.create.CreateSchoolRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateSchoolRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateSchoolResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllSchoolsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetSchoolResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateSchoolResponse;

import java.util.List;


public interface SchoolService {
    List<GetAllSchoolsResponse> getAll();
    GetSchoolResponse getById(int id);
    CreateSchoolResponse add(CreateSchoolRequest request);
    UpdateSchoolResponse update(int id, UpdateSchoolRequest request);
    void delete(int id);
}

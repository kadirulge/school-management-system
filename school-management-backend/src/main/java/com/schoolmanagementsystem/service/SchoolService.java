package com.schoolmanagementsystem.service;

import com.schoolmanagementsystem.dto.requests.create.CreateSchoolRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateSchoolRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateSchoolResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllSchoolsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetSchoolResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateSchoolResponse;
import com.schoolmanagementsystem.entities.School;
import com.schoolmanagementsystem.repository.SchoolRepository;
import com.schoolmanagementsystem.service.rules.SchoolBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository repository;
    private final ModelMapper mapper;
    private final SchoolBusinessRules rules;


    public List<GetAllSchoolsResponse> getAll() {
        final List<School> schools = repository.findAll();
        final List<GetAllSchoolsResponse> response = schools
                .stream()
                .map(school -> mapper.map(school, GetAllSchoolsResponse.class))
                .toList();

        return response;
    }


    public GetSchoolResponse getById(int id) {
        rules.checkIfSchoolExistsById(id);
        final School school = repository.findById(id).orElseThrow();
        final GetSchoolResponse response = mapper.map(school, GetSchoolResponse.class);

        return response;
    }


    public CreateSchoolResponse add(CreateSchoolRequest request) {
        rules.checkIfSchoolExistsByName(request.getName());
        final School school = mapper.map(request, School.class);
        school.setId(0);
        repository.save(school);
        final CreateSchoolResponse response = mapper.map(school, CreateSchoolResponse.class);

        return response;
    }


    public UpdateSchoolResponse update(int id, UpdateSchoolRequest request) {
        rules.checkIfSchoolExistsById(id);
        final School school = mapper.map(request, School.class);
        school.setId(id);
        repository.save(school);
        final UpdateSchoolResponse response = mapper.map(school, UpdateSchoolResponse.class);

        return response;
    }


    public void delete(int id) {
        rules.checkIfSchoolExistsById(id);
        repository.deleteById(id);
    }
}


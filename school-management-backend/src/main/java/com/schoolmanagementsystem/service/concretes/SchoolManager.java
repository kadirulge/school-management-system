package com.schoolmanagementsystem.service.concretes;

import com.schoolmanagementsystem.dto.requests.create.CreateSchoolRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateSchoolRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateSchoolResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllSchoolsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetSchoolResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateSchoolResponse;
import com.schoolmanagementsystem.entities.School;
import com.schoolmanagementsystem.repository.SchoolRepository;
import com.schoolmanagementsystem.service.abstracts.SchoolService;
import com.schoolmanagementsystem.service.rules.SchoolBusinessRules;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolManager implements SchoolService{
    private final SchoolRepository repository;
    private final ModelMapper mapper;
    private final SchoolBusinessRules rules;

    @Override
    public List<GetAllSchoolsResponse> getAll() {
        List<School> schools = repository.findAll();
        List<GetAllSchoolsResponse> response = schools
                .stream()
                .map(school -> mapper.map(school, GetAllSchoolsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetSchoolResponse getById(int id) {
        rules.checkIfSchoolExistsById(id);
        School school = repository.findById(id).orElseThrow();
        GetSchoolResponse response = mapper.map(school, GetSchoolResponse.class);

        return response;
    }

    @Override
    public CreateSchoolResponse add(CreateSchoolRequest request) {
        rules.checkIfSchoolExistsByName(request.getName());
        School school = mapper.map(request, School.class);
        school.setId(0);
        repository.save(school);
        CreateSchoolResponse response = mapper.map(school, CreateSchoolResponse.class);

        return response;
    }

    @Override
    public UpdateSchoolResponse update(int id, UpdateSchoolRequest request) {
        rules.checkIfSchoolExistsById(id);
        School school = mapper.map(request, School.class);
        school.setId(id);
        repository.save(school);
        UpdateSchoolResponse response = mapper.map(school, UpdateSchoolResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSchoolExistsById(id);
        repository.deleteById(id);
    }
}


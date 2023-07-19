package com.schoolmanagementsystem.service.concretes;

import com.schoolmanagementsystem.dto.requests.create.CreateStudentRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateStudentRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllStudentsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetStudentResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateStudentResponse;
import com.schoolmanagementsystem.entities.Student;
import com.schoolmanagementsystem.repository.StudentRepository;
import com.schoolmanagementsystem.service.abstracts.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {
    private final StudentRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllStudentsResponse> getAll() {
        List<Student> students = repository.findAll();
        List<GetAllStudentsResponse> response = students
                .stream()
                .map(student -> mapper.map(student, GetAllStudentsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetStudentResponse getById(int id) {
        Student student = repository.findById(id).orElseThrow();
        GetStudentResponse response = mapper.map(student, GetStudentResponse.class);

        return response;
    }

    @Override
    public CreateStudentResponse add(CreateStudentRequest request) {
        Student student = mapper.map(request, Student.class);
        student.setId(0);
        repository.save(student);
        CreateStudentResponse response = mapper.map(student, CreateStudentResponse.class);

        return response;
    }

    @Override
    public UpdateStudentResponse update(int id, UpdateStudentRequest request) {
        Student student = mapper.map(request, Student.class);
        student.setId(id);
        repository.save(student);
        UpdateStudentResponse response = mapper.map(student, UpdateStudentResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}

package com.schoolmanagementsystem.api.controllers;


import com.schoolmanagementsystem.dto.requests.create.CreateStudentRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateStudentRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllStudentsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetStudentResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateStudentResponse;
import com.schoolmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentsController {
    private final StudentService service;
    private final MessageSource messageSource;

    @GetMapping
    public List<GetAllStudentsResponse> getAll(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        System.out.println(messageSource.getMessage("hello", null, locale));
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetStudentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateStudentResponse add(@Valid @RequestBody CreateStudentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateStudentResponse update(@PathVariable int id,@Valid @RequestBody UpdateStudentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

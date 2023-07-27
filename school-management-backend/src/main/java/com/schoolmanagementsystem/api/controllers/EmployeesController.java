package com.schoolmanagementsystem.api.controllers;


import com.schoolmanagementsystem.common.constants.Roles;
import com.schoolmanagementsystem.dto.requests.create.CreateEmployeeRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateEmployeeRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllEmployeesResponse;
import com.schoolmanagementsystem.dto.responses.get.GetEmployeeResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateEmployeeResponse;
import com.schoolmanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/employees")
public class EmployeesController {
    private final EmployeeService service;


    @GetMapping
    @PreAuthorize(Roles.AdminOrModerator)
    public List<GetAllEmployeesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PostAuthorize(Roles.AdminOrModerator + " || returnObject.id < 4")
    public GetEmployeeResponse getById(@PathVariable int id, @AuthenticationPrincipal Jwt jwt) {
        log.info( jwt.getClaims().get("preferred_username").toString());
        log.info( jwt.getClaims().get("email").toString());
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateEmployeeResponse add(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateEmployeeResponse update(@PathVariable int id,@Valid @RequestBody UpdateEmployeeRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}

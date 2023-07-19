package com.schoolmanagementsystem.service.rules;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolBusinessRules {
    private final SchoolRepository repository;

    public void checkIfSchoolExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.School.NotExists);
        }
    }

    public void checkIfSchoolExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(Messages.School.Exists);
        }
    }

}

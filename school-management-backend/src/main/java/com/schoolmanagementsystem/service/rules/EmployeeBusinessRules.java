package com.schoolmanagementsystem.service.rules;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeBusinessRules {
    private final EmployeeRepository repository;

    public void checkIfEmployeeExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Employee.NotExists);
        }
    }

    public void checkIfEmployeeExistsByEmployeeNumber(String studentNumber) {
        if (repository.existsByEmployeeNumber(studentNumber)) {
            throw new BusinessException(Messages.Employee.Exists);
        }
    }
}

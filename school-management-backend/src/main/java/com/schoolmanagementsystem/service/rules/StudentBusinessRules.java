package com.schoolmanagementsystem.service.rules;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentBusinessRules {
    private final StudentRepository repository;

    public void checkIfStudentExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Student.NotExists);
        }
    }

    public void checkIfStudentExistsByStudentNumber(String studentNumber) {
        if (repository.existsByStudentNumber(studentNumber)) {
            throw new BusinessException(Messages.Student.Exists);
        }
    }

}

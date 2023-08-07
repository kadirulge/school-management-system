package com.schoolmanagementsystem.service;

import com.schoolmanagementsystem.common.constants.Messages;
import com.schoolmanagementsystem.common.events.StudentCreatedEvent;
import com.schoolmanagementsystem.core.exceptions.BusinessException;
import com.schoolmanagementsystem.service.rabbitmq.producer.RabbitMqProducer;
import com.schoolmanagementsystem.dto.requests.create.CreateStudentRequest;
import com.schoolmanagementsystem.dto.requests.update.UpdateStudentRequest;
import com.schoolmanagementsystem.dto.responses.create.CreateStudentResponse;
import com.schoolmanagementsystem.dto.responses.get.GetAllStudentsResponse;
import com.schoolmanagementsystem.dto.responses.get.GetStudentResponse;
import com.schoolmanagementsystem.dto.responses.update.UpdateStudentResponse;
import com.schoolmanagementsystem.entities.Student;
import com.schoolmanagementsystem.repository.StudentRepository;
import com.schoolmanagementsystem.service.rules.StudentBusinessRules;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final ModelMapper mapper;
    private final StudentBusinessRules rules;
    private final RabbitMqProducer producer;

    public List<GetAllStudentsResponse> getAll() {
        final List<Student> students = repository.findAll();
        final List<GetAllStudentsResponse> response = students
                .stream()
                .map(student -> mapper.map(student, GetAllStudentsResponse.class))
                .toList();

        return response;
    }


    public GetStudentResponse getById(int id) {
        final Student student = repository.findById(id).orElseThrow(() -> new BusinessException(Messages.Student.NotExists));;
        final GetStudentResponse response = mapper.map(student, GetStudentResponse.class);

        return response;
    }


    public CreateStudentResponse add(CreateStudentRequest request) {
        rules.checkIfStudentExistsByStudentNumber(request.getStudentNumber());
        final Student student = mapper.map(request, Student.class);
        student.setId(0);
        repository.save(student);

        sendRabbitMqStudentCreatedEvent(request.getSchoolId());
        final CreateStudentResponse response = mapper.map(student, CreateStudentResponse.class);

        return response;
    }


    public UpdateStudentResponse update(int id, UpdateStudentRequest request) {
        rules.checkIfStudentExistsById(id);
        final Student student = mapper.map(request, Student.class);
        student.setId(id);
        repository.save(student);
        final UpdateStudentResponse response = mapper.map(student, UpdateStudentResponse.class);

        return response;
    }

    public void delete(int id) {
        rules.checkIfStudentExistsById(id);
        repository.deleteById(id);
    }


    private void sendRabbitMqStudentCreatedEvent(int schoolId)
    {
        producer.sendMessage(new StudentCreatedEvent(schoolId), "student-created");
    }
}

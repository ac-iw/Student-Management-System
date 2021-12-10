package com.company.studentmanagement;
import com.company.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentManagementService {

    private StudentRepository repository;

    public StudentManagementService(StudentRepository repository) {
        this.repository = repository;
        CLI.startSystem(repository);
    }

}



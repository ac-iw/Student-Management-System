package com.company.studentmanagement;
import com.company.studentmanagement.repository.StudentRepository;

public class StudentManagementService {

    private StudentRepository repository;

    public StudentManagementService(StudentRepository repository) {
        this.repository = repository;
        CLI.startSystem(repository);
    }

}



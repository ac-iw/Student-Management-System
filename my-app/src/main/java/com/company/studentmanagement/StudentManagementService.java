package com.company.studentmanagement;
import com.company.studentmanagement.repository.StudentRepository;


public class StudentManagementService {

    private static StudentRepository repository;

    public StudentManagementService(StudentRepository repository) {
        StudentManagementService.repository = repository;
        CLI.startSystem(repository);
    }

}



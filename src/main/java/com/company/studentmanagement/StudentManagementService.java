package com.company.studentmanagement;
import com.company.studentmanagement.dtos.StudentIn;
import com.company.studentmanagement.repository.StudentRepository;
import com.company.studentmanagement.repository.inmemory.InMemoryStudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class StudentManagementService {

    private StudentRepository repository;

    public StudentManagementService(StudentRepository repository) {
        this.repository = repository;
        // Refactor ClI as spring component
        if (repository.getClass().equals(InMemoryStudentRepository.class)) {
            CLI.startSystem(repository);
        }
    }

}



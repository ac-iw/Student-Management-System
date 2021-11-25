package com.company.studentmanagement;
import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.FullTimeStudent;
import com.company.studentmanagement.domain.PartTimeStudent;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentManagementService {

    private static StudentRepository repository;

    public StudentManagementService(StudentRepository repository) {
        StudentManagementService.repository = repository;
        CLI.startSystem(repository);
    }

}



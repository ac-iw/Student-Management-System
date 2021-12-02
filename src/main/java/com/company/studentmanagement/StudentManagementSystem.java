package com.company.studentmanagement;

import com.company.studentmanagement.repository.StudentRepositoryFactory;
import com.company.studentmanagement.repository.StudentRepository;

public class StudentManagementSystem {

    public static void main(String[] args) {

        StudentRepository repository = StudentRepositoryFactory.getRepository("InMemory");
        StudentManagementService studentManagementService = new StudentManagementService(repository);
    }


}

package com.company.studentmanagement.config;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.FullTimeStudent;
import com.company.studentmanagement.domain.PartTimeStudent;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.helper.PopulateDatabase;
import com.company.studentmanagement.repository.StudentRepository;
import com.company.studentmanagement.repository.StudentRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    public StudentRepository studentRepository() {
        StudentRepository repository = StudentRepositoryFactory.getRepository("InMemory");
        PopulateDatabase.populateDatabase(repository);
        return repository;
    }

}

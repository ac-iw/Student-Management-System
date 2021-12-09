package com.company.studentmanagement.config;

import com.company.studentmanagement.StudentManagementService;
import com.company.studentmanagement.repository.StudentRepository;
import com.company.studentmanagement.repository.StudentRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentManagementConfig {

    @Bean
    public StudentRepositoryFactory studentRepositoryFactory() {
        return new StudentRepositoryFactory();
    }

    @Bean
    public StudentRepository studentRepository() {
        return studentRepositoryFactory().getRepository("InMemory");
    }

    @Bean
    public StudentManagementService studentManagementService() {
        return new StudentManagementService(studentRepository());
    }

}

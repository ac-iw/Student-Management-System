package com.company.studentmanagement.config;

import com.company.studentmanagement.repository.StudentRepository;
import com.company.studentmanagement.repository.database.DatabaseStudentRepository;
import com.company.studentmanagement.repository.inmemory.InMemoryStudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class StudentManagementConfig {

    @ConditionalOnProperty(name = "repository.type", havingValue = "inmemory")
    @Bean
    public StudentRepository inMemoryStudentRepository() {
        return new InMemoryStudentRepository();
    }

    @ConditionalOnProperty(name = "repository.type", havingValue = "database")
    @Bean
    public StudentRepository databaseStudentRepository() {
        return new DatabaseStudentRepository();
    }

}

package com.company.studentmanagement.config;

import com.company.studentmanagement.controllers.StudentManagementController;
import com.company.studentmanagement.repository.StudentRepository;
import com.company.studentmanagement.repository.database.DatabaseStudentRepository;
import com.company.studentmanagement.repository.inmemory.InMemoryStudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Configuration
@ComponentScan
public class StudentManagementConfig {

}

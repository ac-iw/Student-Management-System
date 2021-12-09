package com.company.studentmanagement.repository;

import com.company.studentmanagement.repository.database.DatabaseStudentRepository;
import com.company.studentmanagement.repository.inmemory.InMemoryStudentRepository;

public class StudentRepositoryFactory {

    public StudentRepository getRepository(String repoType) {
        if (repoType == "Database") {
            return new DatabaseStudentRepository();
        } else if (repoType == "InMemory") {
            return new InMemoryStudentRepository();
        }
        return null;
    }
}

package com.company;

public class Main {

    public static void main(String[] args) {

        IRepository dummyRepository = RepositoryFactory.getRepository("Database");
        IRepository actualRepository = RepositoryFactory.getRepository("InMemory");
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem(dummyRepository);
        studentManagementSystem.changeRepository(actualRepository);
        studentManagementSystem.startSystem();
    }


}

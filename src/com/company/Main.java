package com.company;

public class Main {

    public static void main(String[] args) {

        Repository repository = RepositoryFactory.getRepository("InMemory");
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem(repository);
        studentManagementSystem.startSystem();
    }


}

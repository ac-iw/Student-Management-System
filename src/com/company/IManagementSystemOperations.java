package com.company;

import java.util.List;

public interface IManagementSystemOperations {
    public void addStudent(Student newStudent);
    public Student removeStudent(int studentId);
    public Student viewStudent(int studentId);
    public List<Student> viewStudents();
}

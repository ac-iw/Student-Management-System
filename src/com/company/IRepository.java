package com.company;

import java.util.List;

public interface IRepository {
    void addStudent(Student newStudent);
    Student removeStudent(int studentId);
    Student viewStudent(int studentId);
    List<Student> viewStudents();
    boolean studentExists(int studentId);
    void addCourse(Course newCourse);
    Course getCourse(int courseId);
    List<Course> getCourses();
}

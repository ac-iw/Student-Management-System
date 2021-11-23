package com.company;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository extends Repository {

    public DatabaseRepository() {}

    @Override
    public void addStudent(Student newStudent) {
    }

    @Override
    public Student removeStudent(int studentId) {
        return null;
    }

    @Override
    public Student viewStudent(int studentId) {
        return null;
    }

    @Override
    public List<Student> viewStudents() {
        return null;
    }

    @Override
    public boolean studentExists(int studentId) {
        return true;
    }

    @Override
    public void addCourse(Course newCourse) {

    }

    @Override
    public Course getCourse(int courseId) {
        return null;
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }
}

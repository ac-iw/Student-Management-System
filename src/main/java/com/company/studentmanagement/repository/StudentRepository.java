package com.company.studentmanagement.repository;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.Student;

import java.util.List;

public interface StudentRepository {
    //publics
    void addStudent(Student newStudent);

    Student removeStudent(int studentId);

    Student viewStudent(int studentId);

    List<Student> viewStudents();

    boolean studentExists(int studentId);

    void addCourse(Course newCourse);

    Course getCourse(int courseId);

    List<Course> getCourses();

}

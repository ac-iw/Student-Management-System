package com.company.studentmanagement.repository.inmemory;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStudentRepository implements StudentRepository {

    private final Map<Integer, Student> studentMap;
    private final Map<Integer, Course> courseMap;

    public InMemoryStudentRepository() {
        studentMap = new HashMap<Integer, Student>();
        courseMap = new HashMap<Integer, Course>();
    }

    @Override
    public void addStudent(Student newStudent) {
        studentMap.put(newStudent.getId(), newStudent);
    }

    @Override
    public Student removeStudent(int studentId) {
        return studentMap.remove(studentId);
    }

    @Override
    public Student viewStudent(int studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public List<Student> viewStudents() {
        List<Student> students = new ArrayList<>(studentMap.values());
        return students;
    }

    @Override
    public boolean studentExists(int studentId) {
        return studentMap.get(studentId) != null;
    }

    @Override
    public void addCourse(Course newCourse) {
        courseMap.put(newCourse.getId(), newCourse);
    }

    @Override
    public Course getCourse(int courseId) {
        return courseMap.get(courseId);
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>(courseMap.values());
        return courses;
    }


}

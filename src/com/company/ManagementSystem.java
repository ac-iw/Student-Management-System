package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementSystem implements IManagementSystemOperations{
    private Map<Integer, Student> studentMap;
    private Map<Integer, Course> courseMap;
    // Static methods?
    public ManagementSystem() {
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

    public boolean studentExists(int studentId) {
        if (studentMap.get(studentId) == null) {
            return false;
        }
        return true;
    }

    //add to interface
    public void addCourse(Course newCourse) {
        courseMap.put(newCourse.getId(), newCourse);
    }

    public Course getCourse(int courseId) {
        return courseMap.get(courseId);
    }

    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>(courseMap.values());
        return courses;
    }
}

package com.company.studentmanagement.helper;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class ListConverter {
    public static List<Course> IdToCourse(List<Integer> courses, StudentRepository studentRepository) {
        List<Course> newStudentCourses = new ArrayList<>();
        for (Integer courseId: courses) {
            Course course = studentRepository.getCourse(courseId);
            if (course != null) {
                newStudentCourses.add(course);
            }
        }
        return newStudentCourses;
    }
}

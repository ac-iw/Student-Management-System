package com.company.studentmanagement.helper;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.FullTimeStudent;
import com.company.studentmanagement.domain.PartTimeStudent;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class PopulateDatabase {
    public static void populateDatabase(StudentRepository repository) {
        Course course1 = new Course(1, "Maths 350", "Topology");
        Course course2 = new Course(2, "Compsci 361", "Machine Learning");
        Course course3 = new Course(3, "Phil 105", "Critical Thinking");
        Course course4 = new Course(4, "Stats 201", "Data Analysis");
        repository.addCourse(course1);
        repository.addCourse(course2);
        repository.addCourse(course3);
        repository.addCourse(course4);
        List<Course> courses = repository.getCourses();
        List<Course> student1Courses = new ArrayList<>();
        student1Courses.add(courses.get(0));
        student1Courses.add(courses.get(1));
        student1Courses.add(courses.get(2));
        student1Courses.add(courses.get(3));
        List<Course> student2Courses = new ArrayList<>();
        student2Courses.add(courses.get(0));
        student2Courses.add(courses.get(1));
        Student student1 = new FullTimeStudent(1, "Adam", "Chappell", 20, "Sun",
                student1Courses);
        Student student2 = new PartTimeStudent(2, "John", "Appleseed", 37, "Moon",
                student2Courses);
        repository.addStudent(student1);
        repository.addStudent(student2);
    }
}

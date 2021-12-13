package com.company.studentmanagement.controllers;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.FullTimeStudent;
import com.company.studentmanagement.domain.PartTimeStudent;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.dtos.StudentIn;
import com.company.studentmanagement.helper.ListConverter;
import com.company.studentmanagement.helper.PopulateDatabase;
import com.company.studentmanagement.repository.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ConditionalOnProperty(name = "repository.type", havingValue = "database")
@RestController
public class StudentManagementController {

    private final StudentRepository studentRepository;

    public StudentManagementController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/clear")
    void clear() {
        studentRepository.removeData();
    }

    @GetMapping("/populate")
    void populate() {
        PopulateDatabase.populateDatabase(studentRepository);
    }

    @GetMapping("/students")
    List<Student> allStudents() {
        return studentRepository.viewStudents();
    }

    @GetMapping("/student/{id}")
    Student oneStudent(@PathVariable Integer id) {
        return studentRepository.viewStudent(id);
    }

    @PostMapping("/student")
    String addStudent(@RequestBody StudentIn newStudent) {

        List<Course> newStudentCourses = ListConverter.IdToCourse(newStudent.getCourses(), studentRepository);

        Student student = new Student(newStudent.getId(), newStudent.getFirstName(),
                    newStudent.getFamilyName(), newStudent.getAge(),
                    newStudent.getAddress());
        student.setCourses(newStudentCourses);
        studentRepository.addStudent(student);

//        //Converts received list of course id's to list of Course objects
//        List<Course> coursesTaken = ListConverter.IdToCourse(newStudent.getCourses(), studentRepository);
//
//        if (coursesTaken.size() > 2) {
//            FullTimeStudent newFullTimeStudent = new FullTimeStudent(
//                    newStudent.getId(), newStudent.getFirstName(),
//                    newStudent.getFamilyName(), newStudent.getAge(),
//                    newStudent.getAddress(), coursesTaken);
//            studentRepository.addStudent(newFullTimeStudent);
//        } else {
//            PartTimeStudent newPartTimeStudent = new PartTimeStudent(
//                    newStudent.getId(), newStudent.getFirstName(),
//                    newStudent.getFamilyName(), newStudent.getAge(),
//                    newStudent.getAddress(), coursesTaken);
//            studentRepository.addStudent(newPartTimeStudent);
//        }

        return "Student added successfully";
    }

    @DeleteMapping("/student/{id}")
    String deleteStudent(@PathVariable Integer id) {
        Student removedStudent =  studentRepository.removeStudent(id);
        if (removedStudent != null) {
            return "Student with id of " + id + " successfully removed";
        }
        return "Student does not exist";
    }

    @GetMapping("/courses")
    List<Course> allCourses() {
        return studentRepository.getCourses();
    }

    @GetMapping("/course/{id}")
    Course oneCourse(@PathVariable Integer id) {
        return studentRepository.getCourse(id);
    }

    @PostMapping("/course")
    String addCourse(@RequestBody Course newCourse) {
        studentRepository.addCourse(newCourse);
        return "Course added";
    }
}

package com.company.studentmanagement;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.FullTimeStudent;
import com.company.studentmanagement.domain.PartTimeStudent;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.helper.ListConverter;
import com.company.studentmanagement.helper.PopulateDatabase;
import com.company.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static Scanner inputReader;
    private static StudentRepository repository;


    public static void startSystem(StudentRepository r) {
        repository = r;
        inputReader = new Scanner(System.in);
        PopulateDatabase.populateDatabase(r);
        boolean finished = startLoop();
        while (!finished) {
            finished = startLoop();
        }
    }

    private static boolean startLoop() {
        System.out.println("Welcome to UOA's Student Management System\n\n" +
                "To add a student, enter 1\n" +
                "To remove a student, enter 2\n" +
                "To view a student, enter 3\n" +
                "To view all students, enter 4\n" +
                "To exit the Student Management System, enter 0\n");
        int enteredNumber = Integer.parseInt(inputReader.nextLine());
        switch (enteredNumber) {
            case 0:
                System.out.println("Exited at " + java.time.LocalDateTime.now());
                return true;
            case 1:
                addStudent();
                break;
            case 2:
                removeStudent();
                break;
            case 3:
                viewStudent();
                break;
            case 4:
                viewStudents();
                break;
        }
        return false;
    }

    //refactor into two functions
    private static void addStudent() {
        System.out.println("Adding a new student ... \n\n" +
                "Please enter the following credentials of the new student:");
        System.out.println("Id: ");
        int id = Integer.parseInt(inputReader.nextLine());
        if (repository.studentExists(id)) {
            System.out.println("Id already exists");
            return;
        }
        System.out.println("First Name: ");
        String firstName = inputReader.nextLine();
        System.out.println("Family Name: ");
        String familyName = inputReader.nextLine();
        System.out.println("Age: ");
        int age = Integer.parseInt(inputReader.nextLine());
        System.out.println("Address: ");
        String address = inputReader.nextLine();

        System.out.println("Course List:\n");
        String courseList = viewCourseList();
        System.out.println("Id | Name| Description");
        System.out.println(courseList);

        List<Integer> studentsCoursesById = new ArrayList<>();
        System.out.println("Please enter the students courses by Id, if you are done enter -1");
        int courseId = 0;
        while (courseId != -1) {
            System.out.printf("Course %d: %n", studentsCoursesById.size() + 1);
            courseId = Integer.parseInt(inputReader.nextLine());
            if (courseId != -1) {
                studentsCoursesById.add(courseId);
            }
        }

        List<Course> studentsCourses = ListConverter.IdToCourse(studentsCoursesById, repository);

        if (studentsCourses.size() > 2) {
            FullTimeStudent newFullTimeStudent = new FullTimeStudent(id, firstName, familyName, age, address,
                    studentsCourses);
            repository.addStudent(newFullTimeStudent);
        } else {
            PartTimeStudent newPartTimeStudent = new PartTimeStudent(id, firstName, familyName, age, address,
                    studentsCourses);
            repository.addStudent(newPartTimeStudent);
        }
        System.out.println("Student Added!\n");
    }

    private static void removeStudent() {
        System.out.println("Please enter the students id:");
        int studentId = Integer.parseInt((inputReader.nextLine()));
        System.out.println("Searching for student ... \n");
        Student removedStudent = repository.removeStudent(studentId);
        if (removedStudent == null) {
            System.out.println("Student does not exist\n");
        } else {
            System.out.println("id | first name | last name | age | address");
            System.out.println(removedStudent + "\n");
            System.out.println("Student removed successfully\n");
        }
    }

    private static void viewStudent() {
        System.out.println("Please enter the students id:");
        int studentId = Integer.parseInt((inputReader.nextLine()));
        System.out.println("Searching for a student ... \n");
        Student foundStudent = repository.viewStudent(studentId);
        if (foundStudent == null) {
            System.out.println("Student does not exist\n");
        } else {
            System.out.println(foundStudent + "\n\nCourses");
            List<Course> studentCourses = foundStudent.getCourses();
            for (Course c : studentCourses) {
                System.out.println(c);
            }
        }
    }

    private static void viewStudents() {
        System.out.println("Viewing all students ... \n\nid | first name | last name\n");
        List<Student> studentList = repository.viewStudents();
        StringBuilder studentText = new StringBuilder();
        for (Student s : studentList) {
            studentText.append(s.printKeyDetails());
        }
        System.out.println(studentText);
    }

    private static String viewCourseList() {
        List<Course> courseList = repository.getCourses();
        StringBuilder courseText = new StringBuilder();
        for (Course c : courseList) {
            courseText.append(c.toString()).append("\n");
        }
        return courseText.toString();
    }
}


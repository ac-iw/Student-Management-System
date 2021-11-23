package com.company;
import java.util.*;

public class StudentManagementSystem {

    private static Scanner inputReader;
    private static Repository repository;

    public StudentManagementSystem(Repository repository) {
        StudentManagementSystem.repository = repository;
        inputReader = new Scanner(System.in);
    }

    public void startSystem() {
        createCourses();
        createStudents();
        boolean finished = startLoop();
        while (!finished) {
            finished = startLoop();
        }
    }

    private boolean startLoop() {
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
    private void addStudent() {
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

        List<Course> studentsCourses = new ArrayList<>();
        System.out.println("Please enter the students courses by Id, if you are done enter -1");
        int courseId = 0;
        while (courseId != -1) {
            System.out.printf("Course %d: %n", studentsCourses.size()+1);
            courseId = Integer.parseInt(inputReader.nextLine());
            if (courseId != -1) {
                studentsCourses.add(repository.getCourse(courseId));
            }
        }

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

    private void removeStudent() {
        System.out.println("Please enter the students id:");
        int studentId =Integer.parseInt((inputReader.nextLine()));
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

    private void viewStudent() {
        System.out.println("Please enter the students id:");
        int studentId =Integer.parseInt((inputReader.nextLine()));
        System.out.println("Searching for a student ... \n");
        Student foundStudent = repository.viewStudent(studentId);
        if (foundStudent == null) {
            System.out.println("Student does not exist\n");
        } else {
            System.out.println(foundStudent + "\n\nCourses");
            List<Course> studentCourses = foundStudent.getCourses();
            for (Course c: studentCourses) {
                System.out.println(c);
            }
        }
    }

    private void viewStudents() {
        System.out.println("Viewing all students ... \n\nid | first name | last name\n");
        List<Student> studentList = repository.viewStudents();
        StringBuilder studentText = new StringBuilder();
        for (Student s: studentList) {
            studentText.append(s.printKeyDetails());
        }
        System.out.println(studentText);
    }

    private String viewCourseList() {
        List<Course> courseList = repository.getCourses();
        StringBuilder courseText = new StringBuilder();
        for (Course c: courseList) {
            courseText.append(c.toString()).append("\n");
        }
        return courseText.toString();
    }

    private void createStudents() {
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

    private void createCourses() {
        Course course1 = new Course(1, "Maths 350", "Topology");
        Course course2 = new Course(2, "Compsci 361", "Machine Learning");
        Course course3 = new Course(3, "Phil 105", "Critical Thinking");
        Course course4 = new Course(4, "Stats 201", "Data Analysis");
        repository.addCourse(course1);
        repository.addCourse(course2);
        repository.addCourse(course3);
        repository.addCourse(course4);
    }
}

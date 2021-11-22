package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ManagementSystem managementSystem;
    private static Scanner inputReader;

    public static void main(String[] args) {
	// write your code here
        inputReader = new Scanner(System.in);
        managementSystem = new ManagementSystem();
        createCourses();
        createStudents();
        Boolean finished = startLoop();
        while (!finished) {
            finished = startLoop();
        }
    }

    public static boolean startLoop() {
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
    public static void addStudent() {
        System.out.println("Adding a new student ... \n");
        System.out.println("Please enter the following credentials of the new student:");
        System.out.println("Id: ");
        int id = Integer.parseInt(inputReader.nextLine());
        if (managementSystem.studentExists(id)) {
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
            System.out.println(String.format("Course %d: ", studentsCourses.size()+1));
            courseId = Integer.parseInt(inputReader.nextLine());
            if (courseId != -1) {
                studentsCourses.add(managementSystem.getCourse(courseId));
            }
        }


        if (studentsCourses.size() > 2) {
            FullTimeStudent newFullTimeStudent = new FullTimeStudent(id, firstName, familyName, age, address,
                    studentsCourses);
            managementSystem.addStudent(newFullTimeStudent);
        } else {
            PartTimeStudent newPartTimeStudent = new PartTimeStudent(id, firstName, familyName, age, address,
                    studentsCourses);
            managementSystem.addStudent(newPartTimeStudent);
        }
        System.out.println("Student Added!\n");
    }

    public static void removeStudent() {
        System.out.println("Please enter the students id:");
        int studentId =Integer.parseInt((inputReader.nextLine()));
        System.out.println("Searching for student ... \n");
        Student removedStudent = managementSystem.removeStudent(studentId);
        if (removedStudent == null) {
            System.out.println("Student does not exist\n");
        } else {
            System.out.println("id | first name | last name | age | address");
            System.out.println(removedStudent + "\n");
            System.out.println("Student removed successfully\n");
        }
    }

    public static void viewStudent() {
        System.out.println("Please enter the students id:");
        int studentId =Integer.parseInt((inputReader.nextLine()));
        System.out.println("Searching for a student ... \n");
        Student foundStudent = managementSystem.viewStudent(studentId);
        if (foundStudent == null) {
            System.out.println("Student does not exist\n");
        } else {
            System.out.println(foundStudent + "\n");
            System.out.println("Courses");
            List<Course> studentCourses = foundStudent.getCourses();
            for (Course c: studentCourses) {
                System.out.println(c);
            }
        }
    }

    public static void viewStudents() {
        System.out.println("Viewing all students ... \n");
        System.out.println("id | first name | last name\n");
        List<Student> studentList = managementSystem.viewStudents();
        String studentText = "";
        for (Student s: studentList) {
            studentText += s.printKeyDetails();
        }
        System.out.println(studentText);
    }
    
    public static String viewCourseList() {
        List<Course> courseList = managementSystem.getCourses();
        String courseText = "";
        for (Course c: courseList) {
            courseText += c.toString() + "\n";
        }
        return courseText;
    }

    public static void createStudents() {
        List<Course> courses = managementSystem.getCourses();
        List<Course> student1Courses = new ArrayList<Course>();
        student1Courses.add(courses.get(0));
        student1Courses.add(courses.get(1));
        student1Courses.add(courses.get(2));
        student1Courses.add(courses.get(3));
        List<Course> student2Courses = new ArrayList<Course>();
        student2Courses.add(courses.get(0));
        student2Courses.add(courses.get(1));
        Student student1 = new FullTimeStudent(1, "Adam", "Chappell", 20, "Sun",
                student1Courses);
        Student student2 = new PartTimeStudent(2, "John", "Appleseed", 37, "Moon",
                student2Courses);
        managementSystem.addStudent(student1);
        managementSystem.addStudent(student2);
    }

    public static void createCourses() {
        Course course1 = new Course(1, "Maths 350", "Topology");
        Course course2 = new Course(2, "Compsci 361", "Machine Learning");
        Course course3 = new Course(3, "Phil 105", "Critical Thinking");
        Course course4 = new Course(4, "Stats 201", "Data Analysis");
        managementSystem.addCourse(course1);
        managementSystem.addCourse(course2);
        managementSystem.addCourse(course3);
        managementSystem.addCourse(course4);
    }
}

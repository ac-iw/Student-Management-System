package com.company.studentmanagement.domain;

import java.util.List;

public abstract class Student {
    private List<Course> courses;
    private final int id;
    private final String firstName;
    private final String familyName;
    private final int age;
    private final String address;

    public Student(int id, String firstName, String familyName, int age, String address) {
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
        this.address = address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String printKeyDetails() {
        return String.format("%d | %s | %s\n", id, firstName, familyName);
    }

}

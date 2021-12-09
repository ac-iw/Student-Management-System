package com.company.studentmanagement.dtos;

import com.company.studentmanagement.domain.Course;

import java.util.List;

public class StudentIn {
    private final List<Integer> courses;
    private final int id;
    private final String firstName;
    private final String familyName;
    private final int age;
    private final String address;

    public StudentIn(List<Integer> courses, int id, String firstName, String familyName, int age, String address) {
        this.courses = courses;
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentIn{" +
                "courses=" + courses +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public List<Integer> getCourses() {
        return courses;
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
}

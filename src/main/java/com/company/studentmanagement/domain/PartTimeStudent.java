package com.company.studentmanagement.domain;

import java.util.List;

public class PartTimeStudent extends Student{

    public PartTimeStudent(int id, String firstName, String familyName, int age, String address, List<Course> courses) {
        super(id, firstName, familyName, age, address);
        if (courses.size() <= 2) {
            super.setCourses(courses);
        }
    }

    @Override
    public String toString() {
        return String.format("Id: %d\nFirst Name: %s\nFamily Name: %s\nAge: %d\nAddress %s\nPart Time",
                super.getId(), super.getFirstName(), super.getFamilyName(), super.getAge(), super.getAddress());
    }

}

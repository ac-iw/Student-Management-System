package com.company.studentmanagement.domain;

public class Course {
    private final int id;
    private final String name;
    private final String description;

    public Course() {
        this.id = -1;
        this.name = null;
        this.description = null;
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return String.format("%d | %s | %s", id, name, description);
    }
}

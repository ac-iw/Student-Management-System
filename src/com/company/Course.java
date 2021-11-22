package com.company;

public class Course {
    private int id;
    private String name;
    private String description;

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

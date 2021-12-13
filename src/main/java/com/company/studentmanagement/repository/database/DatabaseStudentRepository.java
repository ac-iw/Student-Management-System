package com.company.studentmanagement.repository.database;

import com.company.studentmanagement.domain.Course;
import com.company.studentmanagement.domain.Student;
import com.company.studentmanagement.repository.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ConditionalOnProperty(name = "repository.type", havingValue = "database")
@Component
public class DatabaseStudentRepository implements StudentRepository {

    Connection connection;

    public DatabaseStudentRepository() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/studentmanagementsystem",
                            "postgres", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS public.students" +
            "(id serial NOT NULL, firstname varchar(200), familyname varchar(200), address varchar(200), age integer," +
                    "CONSTRAINT students_pkey PRIMARY KEY (id))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS public.courses" +
                    "(id serial NOT NULL, name varchar(200), description varchar(200)," +
                    "CONSTRAINT courses_pkey PRIMARY KEY (id))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS public.courses_taken" +
                    "(id serial NOT NULL, student_id integer REFERENCES students," +
                    "course_id integer REFERENCES courses," +
                    "CONSTRAINT courses_taken_pkey PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println(e);;
        }

//        try (Statement stmt = connection.createStatement()) {
//            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS public.courses_taken " +
//                    "    id serial NOT NULL)," +
//                    "    CONSTRAINT courses_taken_pkey PRIMARY KEY (id), " +
//                    "    CONSTRAINT course_id FOREIGN KEY (id) " +
//                    "        REFERENCES public.courses (id) MATCH SIMPLE " +
//                    "        ON UPDATE NO ACTION " +
//                    "        ON DELETE NO ACTION " +
//                    "        NOT VALID, " +
//                    "    CONSTRAINT student_id FOREIGN KEY (id) " +
//                    "        REFERENCES public.students (id) MATCH SIMPLE " +
//                    "        ON UPDATE NO ACTION " +
//                    "        ON DELETE NO ACTION " +
//                    "        NOT VALID " +
//                    ")");
//        }
    }

    @Override
    public void addStudent(Student newStudent) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(String.format(
                    "INSERT INTO Students (firstname, familyname, address, age) " +
                    "VALUES ('%s', '%s', '%s', %d)",
                    newStudent.getFirstName(), newStudent.getFamilyName(),
                    newStudent.getAddress(), newStudent.getAge()));
        } catch (SQLException e) {
            System.out.println(e);
        }



        for (Course c: newStudent.getCourses()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(String.format(
                        "INSERT INTO courses_taken (student_id, course_id) " +
                                "VALUES (%d, %d)", newStudent.getId(), c.getId()));
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public Student removeStudent(int studentId) {
        return null;
    }

    @Override
    public Student viewStudent(int studentId) {
        Student student = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE students.id="+studentId);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String familyname = rs.getString("familyname");
                String address = rs.getString("address");
                int age = rs.getInt("age");
                student = new Student(id, firstname, familyname, age, address);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }

    @Override
    public List<Student> viewStudents() {
        List<Student> allStudents = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String familyname = rs.getString("familyname");
                String address = rs.getString("address");
                int age = rs.getInt("age");
                allStudents.add(new Student(id, firstname, familyname, age, address));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allStudents;
    }

    @Override
    public boolean studentExists(int studentId) {
        try (Statement stmt = connection.createStatement()) {
            int c = stmt.executeUpdate("SELECT COUNT(*) FROM students where students.id="+studentId);
            System.out.println(c);
        } catch (SQLException e) {
            System.out.println(e);;
        }
        return true;
    }

    @Override
    public void addCourse(Course newCourse) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(String.format("INSERT INTO Courses (name, description) VALUES ('%s', '%s')",
                    newCourse.getName(), newCourse.getDescription()));
        } catch (SQLException e) {
            System.out.println(e);;
        }
    }

    @Override
    public Course getCourse(int courseId) {
        Course course = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM courses WHERE courses.id="+courseId);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                course = new Course(id, name, description);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return course;
    }

    @Override
    public List<Course> getCourses() {
        List<Course> allCourses = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                allCourses.add(new Course(id, name, description));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return allCourses;
    }

    public void removeData() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeQuery("DELETE FROM courses; DELETE FROM students");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

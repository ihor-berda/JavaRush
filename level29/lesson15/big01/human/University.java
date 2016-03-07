package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student result = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                result = student;
                break;
            }
        }
        return result;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student result = students.get(0);
        double maxAverageGrade = students.get(0).getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() > maxAverageGrade) {
                maxAverageGrade = student.getAverageGrade();
                result = student;
            }
        }
        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student result = students.get(0);
        double minAverageGrade = students.get(0).getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() < minAverageGrade) {
                minAverageGrade = student.getAverageGrade();
                result = student;
            }
        }
        return result;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}

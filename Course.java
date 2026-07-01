/*
 * Name: Francisco Gil Mendoza
 * Date: 06/30/2026
 * Program: ArrayLists and Student Grades Data
 */ 

import java.util.ArrayList;

public class Course {

    private String courseName;
    private ArrayList<Integer> courseGrades; // index 0=A, 1=B, 2=C, 3=D, 4=F

    // Constructor
    public Course(String courseName, ArrayList<Integer> courseGrades) {
        this.courseName = courseName;
        this.courseGrades = courseGrades;
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }

    // Total grades
    public int getTotalGrades() {
        int total = 0;
        for (int value : courseGrades) {
            total += value;
        }
        return total;
    }

    // Return percent of A
    public double getAPercent() {
        int total = getTotalGrades();
        if (total == 0) return 0.0;
        return (double) courseGrades.get(0) / total * 100.0;
    }

    // toString
    @Override
    public String toString() {
        return courseName + " " + courseGrades.toString();
    }
}

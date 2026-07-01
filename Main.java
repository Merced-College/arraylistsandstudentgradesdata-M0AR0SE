/*
 * Name: Francisco Gil Mendoza
 * Date: 06/30/2026
 * Program: ArrayLists and Student Grades Data
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("courseAndGradesData.csv"));

            // Skip first TWO lines
            br.readLine(); // header line
            br.readLine(); // second line

            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue; // skip blank lines

                String[] parts = line.split(",");

                String courseName = parts[0];
                ArrayList<Integer> grades = new ArrayList<>();

                for (int i = 1; i < parts.length; i++) {
                    grades.add(Integer.parseInt(parts[i].trim()));
                }

                // Check for duplicates
                Course existing = findCourse(courses, courseName);

                if (existing != null) {
                    for (int i = 0; i < grades.size(); i++) {
                        int updated = existing.getCourseGrades().get(i) + grades.get(i);
                        existing.getCourseGrades().set(i, updated);
                    }
                } else {
                    courses.add(new Course(courseName, grades));
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Print summary table
        System.out.printf("%-10s %5s %5s %5s %5s %5s %7s %7s\n",
                "Course", "A", "B", "C", "D", "F", "Total", "A%");

        for (Course c : courses) {
            ArrayList<Integer> g = c.getCourseGrades();
            System.out.printf("%-10s %5d %5d %5d %5d %5d %7d %7.2f\n",
                    c.getCourseName(),
                    g.get(0), g.get(1), g.get(2), g.get(3), g.get(4),
                    c.getTotalGrades(),
                    c.getAPercent());
        }

        // Find highest A%
        Course best = courses.get(0);
        for (Course c : courses) {
            if (c.getAPercent() > best.getAPercent()) {
                best = c;
            }
        }

        System.out.println("\nCourse with highest A%:");
        System.out.println(best.getCourseName() + " - " + String.format("%.2f", best.getAPercent()) + "%");

        // User search
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a course to search: ");
        String searchName = sc.nextLine();

        Course found = findCourse(courses, searchName);

        if (found != null) {
            System.out.println("\nCourse Found:");
            System.out.println("Name: " + found.getCourseName());
            System.out.println("Total Grades: " + found.getTotalGrades());
            System.out.println("A%: " + String.format("%.2f", found.getAPercent()));
        } else {
            System.out.println("Course not found.");
        }

        sc.close(); // FIXES the resource leak warning
    }

    // Linear search helper
    public static Course findCourse(ArrayList<Course> list, String name) {
        for (Course c : list) {
            if (c.getCourseName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}

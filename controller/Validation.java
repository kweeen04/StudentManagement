package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Report;
import model.Student;

public class Validation {

    private static Scanner in = new Scanner(System.in);
    public static int checkInput(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please enter number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String checkStringInput() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input must be not empty.");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkYN() {
        while (true) {
            String result = checkStringInput();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }

            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input Y/y or N/n");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkUD() {
        while (true) {
            String result = checkStringInput();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }

            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input U/u or D/d");
            System.out.print("Enter again: ");
        }
    }

    public static String checkInputCourse() {
        while (true) {
            String result = checkStringInput();
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkStudentExist(ArrayList<Student> ls, String id, String studentName, String semester, String courseName) {
        int size = ls.size();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkReportExist(ArrayList<Report> lr, String name, String course, int total) {
        for (Report report : lr) {
            if (name.equalsIgnoreCase(report.getStudentName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIdExist(ArrayList<Student> ls, String id, String name) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkChangeInfomation(Student student, String id,
            String name, String semester, String course) {
        if (id.equalsIgnoreCase(student.getId())
                && name.equalsIgnoreCase(student.getStudentName())
                && semester.equalsIgnoreCase(student.getSemester())
                && course.equalsIgnoreCase(student.getCourseName())) {
            return false;
        }
        return true;
    }

}

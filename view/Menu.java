package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import controller.Validation;
import model.Report;
import model.Student;

public class Menu {
    
    public static void menu() {
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        System.out.print(" Enter selection: ");
    }

    public static void createStudent(int count, ArrayList<Student> ls) {
        if (count > 10) {
            System.out.print("Do you want to continue:(Y/ N): ");
            if (!Validation.checkYN()) {
                return;
            }
        }

        while (true) {
            System.out.print("Input ID: ");
            String id = Validation.checkStringInput();
            System.out.print("Input student's name: ");
            String name = Validation.checkStringInput();
            if (!Validation.checkIdExist(ls, id, name)) {
                System.err.println("ID has already existed, please input again.");
                continue;
            }

            System.out.print("Input semester: ");
            String semester = Validation.checkStringInput();
            System.out.print("Input course's name: ");
            String course = Validation.checkInputCourse();

            if(Validation.checkStudentExist(ls, id, name, semester, course)) {
                ls.add(new Student(id, name, semester, course));
                count ++;
                System.out.println("Add student successfully.");
                return;
            }
            System.err.println("Duplicated.");
        }
    }


    public static void findSort(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }

        ArrayList<Student> listFindByName = listFindByName(ls);
        if (listFindByName.isEmpty()) {
            System.err.println("Student not existed.");
        } else {
            Collections.sort(listFindByName);
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
            for (Student student : listFindByName) {
                student.display();
            }
        }
    }

    public static ArrayList<Student> listFindByName(ArrayList<Student> ls) {
        ArrayList<Student> listFindByName = new ArrayList<>();
        System.out.print("Input name to search: ");
        String name = Validation.checkStringInput();
        for (Student student : ls) {
            if (student.getStudentName().contains(name)) {
                listFindByName.add(student);
            }
        }
        return listFindByName;
    }

    public static void updateDelete(int count, ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }

        System.out.print("Input id: ");
        String id = Validation.checkStringInput();
        ArrayList<Student> listFindByName = getListFindByID(ls, id);

        if (listFindByName.isEmpty()) {
            System.err.println("Student not found.");
            return;
        } else {
            Student student = getStudentByListFound(listFindByName);
            System.out.print("Do you want to update or delete student: (U/ D): ");
            if (Validation.checkUD()) {
                System.out.print("Input ID: ");
                String studentID = Validation.checkStringInput();
                System.out.print("Input student's name: ");
                String name = Validation.checkStringInput();
                System.out.print("Input semester: ");
                String semester = Validation.checkStringInput();
                System.out.print("Input course's name: ");
                String course = Validation.checkInputCourse();

                if (!Validation.checkChangeInfomation(student, studentID, name, semester, course)) {
                    System.err.println("Nothing changed!");
                }

                if (Validation.checkStudentExist(ls, id, studentID, semester, course)) {
                    student.setId(studentID);
                    student.setStudentName(name);
                    student.setSemester(semester);
                    student.setCourseName(course);
                    System.err.println("Updated info successfully.");
                }
                return;
            } else {
                ls.remove(student);
                count --;
                System.err.println("Delete student successfully");

                return;
            }
        }
    }


    public static Student getStudentByListFound(ArrayList<Student> listFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name", "semester", "Course Name");

        for (Student student : listFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count, student.getStudentName(), student.getSemester(), student.getCourseName());
            count ++;
        }
        System.out.print("Input Student: ");
        int choice = Validation.checkInput(1, listFindByName.size());
        return listFindByName.get(choice - 1);
    }

    public static ArrayList<Student> getListFindByID(ArrayList<Student> ls, String id) {
        ArrayList<Student> getListFindByID = new ArrayList<>();
        
        for (Student student : ls ) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListFindByID.add(student);
            }
        }
        return getListFindByID;
    }


    public static void report(ArrayList<Student> ls) {
    if (ls.isEmpty()) {
        System.err.println("List empty.");
        return;
    }

    HashMap<String, HashMap<String, Integer>> reportData = new HashMap<>();

    for (Student student : ls) {
        String id = student.getId();
        String courseName = student.getCourseName();
        String studentName = student.getStudentName();

        HashMap<String, Integer> studentCourses = reportData.computeIfAbsent(studentName, k -> new HashMap<>());

        studentCourses.put(courseName, studentCourses.getOrDefault(courseName, 0) + 1);
    }

    System.out.printf("%-15s|%-10s|%-5s\n", "Student Name", "Course Name", "Total");
    for (Map.Entry<String, HashMap<String, Integer>> entry : reportData.entrySet()) {
        String studentName = entry.getKey();
        HashMap<String, Integer> studentCourses = entry.getValue();
        for (Map.Entry<String, Integer> courseEntry : studentCourses.entrySet()) {
            String courseName = courseEntry.getKey();
            int total = courseEntry.getValue();
            System.out.printf("%-15s|%-10s |%-5d\n", studentName, courseName, total);
        }
    }
}

}


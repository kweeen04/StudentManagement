package model;

public class Student implements Comparable<Student> {
    private String id;
    private String studentName;
    private String semester;
    private String courseName;

    public Student() {}

    public Student(String id, String studentName, String semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int compareTo(Student s) {
        return s.studentName.compareTo(this.studentName);
    }

    public void display() {
        System.out.printf("%-15s%-15s%-15s\n", studentName, semester, courseName);
    }
}
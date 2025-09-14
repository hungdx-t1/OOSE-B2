package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String major; // ngành đào tạo
    private double gpa; // điểm trung bình
    private String className;

    // Constructor
    public Student(String id, String name, LocalDate dateOfBirth, String major, double gpa, String className) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.gpa = gpa;
        this.className = className;
    }

    // Tạo dữ liệu ảo
    public static List<Student> generateVirtualData() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("4551050001", "Nguyen Van A", LocalDate.of(2002, 5, 10), "CNTT", 8.2, "CNTTK45B"));
        students.add(new Student("4551050002", "Tran Thi B", LocalDate.of(2003, 3, 22), "CNTT", 7.5, "CNTTK45A"));
        students.add(new Student("4551050003", "Le Van Hau", LocalDate.of(2001, 12, 15), "CNTT", 9.0, "CNTTK45C"));
        students.add(new Student("4551050004", "Pham Thi Duyen", LocalDate.of(2002, 8, 5), "CNTT", 6.8, "CNTTK45D"));

        students.add(new Student("4551090001", "Hoang Van Minh", LocalDate.of(2000, 11, 30), "KTPM", 7.9, "KTPM45"));
        students.add(new Student("4551090002", "Do Thi Tuyet Loan", LocalDate.of(2002, 1, 18), "KTPM", 8.6, "KTPM45"));
        students.add(new Student("4551090003", "Bui Van Hai", LocalDate.of(2001, 9, 25), "KTPM", 5.4, "KTPM45"));
        students.add(new Student("4551090004", "Dang Thi Bich Hai", LocalDate.of(2003, 6, 9), "KTPM", 9.3, "KTPM45"));

        students.add(new Student("4551050005", "Nguyen Van Yen", LocalDate.of(2002, 4, 12), "CNTT", 7.0, "CNTTK45A"));
        students.add(new Student("4551090005", "Tran Thi Hoai Linh", LocalDate.of(2001, 7, 21), "KTPM", 6.5, "CNTTK45B"));

        return students;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public String getClassName() {
        return className;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %.2f | %s",
                id, name, dateOfBirth, major, gpa, className);
    }
}

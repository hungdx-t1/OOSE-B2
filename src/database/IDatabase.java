package database;

import models.Student;

import java.sql.SQLException;
import java.util.List;

public interface IDatabase {
    void addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(String id) throws SQLException;
    List<Student> getAll() throws SQLException;
    List<Student> getByClass(String className) throws SQLException;
    List<Student> getByMajor(String major) throws SQLException;
    List<Student> getByMonth(int month) throws SQLException;
    List<Student> getSortedByGpa() throws SQLException;
}

package database;

import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase implements IDatabase {

    private final Connection conn;

    public StudentDatabase() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, dob, major, gpa, class_name) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getId());
        ps.setString(2, student.getName());
        ps.setDate(3, Date.valueOf(student.getDateOfBirth()));
        ps.setString(4, student.getMajor());
        ps.setDouble(5, student.getGpa());
        ps.setString(6, student.getClassName());
        ps.executeUpdate();
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, dob=?, major=?, gpa=?, class_name=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setDate(2, Date.valueOf(student.getDateOfBirth()));
        ps.setString(3, student.getMajor());
        ps.setDouble(4, student.getGpa());
        ps.setString(5, student.getClassName());
        ps.setString(6, student.getId());
        ps.executeUpdate();
    }

    @Override
    public void deleteStudent(String id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public List<Student> getByClass(String className) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE class_name=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, className);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) list.add(mapRow(rs));
        return list;
    }

    @Override
    public List<Student> getByMajor(String major) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE major=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, major);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) list.add(mapRow(rs));
        return list;
    }

    @Override
    public List<Student> getByMonth(int month) throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE MONTH(dob)=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) list.add(mapRow(rs));
        return list;
    }

    @Override
    public List<Student> getSortedByGpa() throws SQLException {
        List<Student> list = new ArrayList<>();
        ResultSet rs = conn.createStatement()
                .executeQuery("SELECT * FROM students ORDER BY gpa DESC");
        while (rs.next()) list.add(mapRow(rs));
        return list;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
                rs.getString("id"),
                rs.getString("name"),
                rs.getDate("dob").toLocalDate(),
                rs.getString("major"),
                rs.getDouble("gpa"),
                rs.getString("class_name")
        );
    }
}

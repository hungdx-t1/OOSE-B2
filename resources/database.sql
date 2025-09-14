CREATE DATABASE qlsv;
USE qlsv;
CREATE TABLE students (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    major ENUM('CNTT', 'KTPM') NOT NULL,
    gpa DOUBLE CHECK(gpa >= 0.0 AND gpa <= 10.0),
    class_name VARCHAR(20) NOT NULL
);

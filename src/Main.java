import database.IDatabase;
import database.StudentDatabase;
import models.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static IDatabase database;

    public static void main(String[] args) throws SQLException {
        try {
            database = new StudentDatabase();
        } catch (SQLException e) {
            System.out.println("Không thể kết nối CSDL: " + e.getMessage());
            return;
        }

        // Duyệt lần đầu
        List<Student> studentsInit = Student.generateVirtualData();
        for(Student student : studentsInit) {
            database.addStudent(student);
        }

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Xóa sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Danh sách tất cả SV");
            System.out.println("5. Danh sách SV theo lớp");
            System.out.println("6. Danh sách SV theo ngành");
            System.out.println("7. Danh sách SV sắp xếp theo GPA");
            System.out.println("8. Danh sách SV sinh trong tháng nhập vào");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> deleteStudent();
                case 3 -> updateStudent();
                case 4 -> printList(database.getAll());
                case 5 -> {
                    System.out.print("Nhập lớp: ");
                    printList(database.getByClass(sc.nextLine()));
                }
                case 6 -> {
                    System.out.print("Nhập ngành (CNTT/KTPM): ");
                    printList(database.getByMajor(sc.nextLine()));
                }
                case 7 -> printList(database.getSortedByGpa());
                case 8 -> {
                    System.out.print("Nhập tháng (1-12): ");
                    printList(database.getByMonth(Integer.parseInt(sc.nextLine())));
                }
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
            System.out.println("\nNhấn phím bất kỳ để tiếp tục...");
            sc.nextLine();
        }
    }

    // Other function
    private static void addStudent() throws SQLException {
        System.out.print("Mã SV: ");
        String id = sc.nextLine();
        if((!id.startsWith("455105") || !id.startsWith("455109")) && id.length() != 10) {
            System.out.println("Mã SV không hợp lệ!");
            return;
        }

        System.out.print("Họ tên: ");
        String name = sc.nextLine();

        System.out.print("Ngày sinh (yyyy-MM-dd): ");
        LocalDate dob = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        int age = LocalDate.now().getYear() - dob.getYear();
        if (age < 15 || age > 110) {
            System.out.println("Tuổi không hợp lệ!");
            return;
        }

        System.out.print("Ngành (CNTT/KTPM): ");
        String major = sc.nextLine();
        if (!major.equals("CNTT") && !major.equals("KTPM")) {
            System.out.println("Ngành không hợp lệ!");
            return;
        }

        System.out.print("Điểm TB: ");
        double gpa = Double.parseDouble(sc.nextLine());
        if (gpa < 0.0 || gpa > 10.0) {
            System.out.println("Điểm TB không hợp lệ!");
            return;
        }

        System.out.print("Lớp sinh hoạt: ");
        String className = sc.nextLine();

        Student sv = new Student(id, name, dob, major, gpa, className);
        database.addStudent(sv);
        System.out.println("Thêm thành công!");
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Nhập mã SV cần xóa: ");
        database.deleteStudent(sc.nextLine());
        System.out.println("Đã xóa!");
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Nhập mã SV cần sửa: ");
        String id = sc.nextLine();

        System.out.print("Tên mới: ");
        String name = sc.nextLine();

        System.out.print("Ngày sinh mới (yyyy-MM-dd): ");
        LocalDate dob = LocalDate.parse(sc.nextLine());

        System.out.print("Ngành mới (CNTT/KTPM): ");
        String major = sc.nextLine();

        System.out.print("Điểm TB mới: ");
        double gpa = Double.parseDouble(sc.nextLine());

        System.out.print("Lớp mới: ");
        String className = sc.nextLine();

        Student sv = new Student(id, name, dob, major, gpa, className);
        database.updateStudent(sv);
        System.out.println("Đã cập nhật!");
    }

    private static void printList(List<Student> list) {
        if (list.isEmpty()) System.out.println("Không có dữ liệu!");
        else for (Student student : list) {
            System.out.println(student);
        }
    }
}
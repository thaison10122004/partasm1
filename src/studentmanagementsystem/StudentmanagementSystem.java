package studentmanagementsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Lớp đại diện cho sinh viên
class Student {
    private String name;
    private int age;
    private String id;
    private double marks; // Thêm thuộc tính điểm

    public Student(String name, int age, String id, double marks) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.marks = marks; // Thêm điểm
    }  

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public double getMarks() {
        return marks; // Lấy điểm
    }

    public void setMarks(double marks) {
        this.marks = marks; // Cập nhật điểm
    }

    public String getRanking() {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Marks: " + marks + ", Rank: " + getRanking();
    }

    void setAge(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setName(String newName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// Lớp quản lý sinh viên
public class StudentmanagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students");
            System.out.println("6. Search Students");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau khi nhập số

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    sortStudents();
                    break;
                case 6:
                    searchStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }

    // Hàm thêm sinh viên
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble(); // Nhập điểm sinh viên
        scanner.nextLine(); // Đọc dòng trống sau khi nhập số

        Student student = new Student(name, age, id, marks);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // Hàm sửa thông tin sinh viên
    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new name (leave blank to keep unchanged): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    student.setName(newName);
                }
                System.out.print("Enter new age (leave blank to keep unchanged): ");
                String ageInput = scanner.nextLine();
                if (!ageInput.isEmpty()) {
                    student.setAge(Integer.parseInt(ageInput));
                }
                System.out.print("Enter new marks (leave blank to keep unchanged): ");
                String marksInput = scanner.nextLine();
                if (!marksInput.isEmpty()) {
                    student.setMarks(Double.parseDouble(marksInput));
                }
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    // Hàm xóa sinh viên
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    // Hàm hiển thị danh sách sinh viên
    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        System.out.println("\n=== Student List ===");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Hàm sắp xếp sinh viên
    private static void sortStudents() {
        System.out.println("Sort by: 1. Name  2. Age  3. ID  4. Marks");
        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống

        switch (sortChoice) {
            case 1:
                Collections.sort(students, Comparator.comparing(Student::getName));
                System.out.println("Students sorted by name.");
                break;
            case 2:
                Collections.sort(students, Comparator.comparingInt(Student::getAge));
                System.out.println("Students sorted by age.");
                break;
            case 3:
                Collections.sort(students, Comparator.comparing(Student::getId));
                System.out.println("Students sorted by ID.");
                break;
            case 4:
                Collections.sort(students, Comparator.comparingDouble(Student::getMarks));
                System.out.println("Students sorted by marks.");
                break;
            default:
                System.out.println("Invalid sort choice.");
        }
        displayStudents();
    }
 // Hàm Bubble Sort theo tên
    private static void bubbleSortByName(ArrayList<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getName().compareTo(students.get(j + 1).getName()) > 0) {
                    // Hoán đổi
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
    // Hàm tìm kiếm sinh viên
    private static void searchStudents() {
        System.out.println("Search by: 1. Name  2. ID");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống

        switch (searchChoice) {
            case 1:
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                for (Student student : students) {
                    if (student.getName().equalsIgnoreCase(name)) {
                        System.out.println(student);
                    }
                }
                break;
            case 2:
                System.out.print("Enter student ID: ");
                String id = scanner.nextLine();
                for (Student student : students) {
                    if (student.getId().equalsIgnoreCase(id)) {
                        System.out.println(student);
                    }
                }
                break;
            default:
                System.out.println("Invalid search choice.");
                
        }
    }
}

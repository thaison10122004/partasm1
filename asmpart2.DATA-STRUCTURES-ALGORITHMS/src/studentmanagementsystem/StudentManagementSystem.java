package studentmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

class Student {
    private String name;
    private int age;
    private String id;
    private double marks;

    public Student(String name, int age, String id, double marks) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.marks = marks;
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
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
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
}

public class StudentManagementSystem {
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
            choice = getValidatedInteger();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> displayStudents();
                case 5 -> sortStudents();
                case 6 -> searchStudents();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);
    }

    private static int getValidatedInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine();  // Clear buffer
        }
        int value = scanner.nextInt();
        scanner.nextLine();  // Clear buffer after integer input
        return value;
    }

    private static double getValidatedDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();  // Clear buffer
        }
        double value = scanner.nextDouble();
        scanner.nextLine();  // Clear buffer after double input
        return value;
    }

    private static String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: This field cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static boolean isIdUnique(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    private static void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine().trim();
            if (!isIdUnique(id)) {
                System.out.println("Error: Student ID already exists.");
                return;
            }

            String name = getNonEmptyInput("Enter Student Name: ");
            System.out.print("Enter Student Age: ");
            int age = getValidatedInteger();
            if (age < 0) { 
                System.out.println("Error: Age cannot be negative."); 
                return;
            }

            System.out.print("Enter Student Marks: ");
            double marks = getValidatedDouble();
            if (marks < 0) {
                System.out.println("Error: Marks cannot be negative.");
                return;
            }

            students.add(new Student(name, age, id, marks));
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine().trim();

        for (Student student : students) {
            if (student.getId().equals(id)) {
                String newName = getNonEmptyInput("Enter new name (leave blank to keep unchanged): ");
                if (!newName.isEmpty()) {
                    student.setName(newName);
                }
                System.out.print("Enter new age (leave blank to keep unchanged): ");
                String ageInput = scanner.nextLine().trim();
                if (!ageInput.isEmpty()) {
                    student.setAge(Integer.parseInt(ageInput));
                }

                System.out.print("Enter new marks (leave blank to keep unchanged): ");
                String marksInput = scanner.nextLine().trim();
                if (!marksInput.isEmpty()) {
                    student.setMarks(Double.parseDouble(marksInput));
                }

                System.out.println("Student updated successfully!");
                return;  // Nếu cập nhật thành công, thoát khỏi phương thức.
            }
        }

        // Nếu không tìm thấy sinh viên, thông báo lỗi
        System.out.println("Student ID not found. Cannot edit.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine().trim();

        boolean studentFound = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                System.out.println("Student deleted successfully!");
                studentFound = true;
                break;  // Nếu xóa thành công, thoát khỏi vòng lặp.
            }
        }

        if (!studentFound) {
            // Nếu không tìm thấy sinh viên, thông báo lỗi
            System.out.println("Error: Student ID not found! Cannot delete.");
        }
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n=== Student List ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    private static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        System.out.println("Sort by: 1. Name  2. Age  3. ID  4. Marks (Bubble Sort)  5. Marks (Quick Sort)");
        int sortChoice = getValidatedInteger();

        switch (sortChoice) {
            case 1 -> students.sort(Comparator.comparing(Student::getName));
            case 2 -> students.sort(Comparator.comparingInt(Student::getAge));
            case 3 -> students.sort(Comparator.comparing(Student::getId));
            case 4 -> bubbleSortByName();
            case 5 -> quickSortStudents();
            default -> System.out.println("Invalid sort choice.");
        }
        displayStudents();
    }

    private static void bubbleSortByName() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getName().compareTo(students.get(j + 1).getName()) > 0) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted by Bubble Sort.");
    }

    private static void quickSortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        quickSortByMarks(students, 0, students.size() - 1);
        System.out.println("Students sorted by marks using Quick Sort.");
    }

    private static void quickSortByMarks(ArrayList<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSortByMarks(students, low, pi - 1);
            quickSortByMarks(students, pi + 1, high);
        }
    }

    private static int partition(ArrayList<Student> students, int low, int high) {
        double pivot = students.get(high).getMarks();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() <= pivot) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }

    private static void searchStudents() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine().trim();
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student ID not found.");
        }
    }
}

// 2200040073 - M. Arthi Sri

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.io.*;

class Student {
    int id;
    String name;
    String course;

    Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Course: " + course);
    }

    // Method to update student details
    void updateDetails(String name, String course) {
        this.name = name;
        this.course = course;
    }
}

public class StudentRecordSystem {
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Load data from file
        loadData();

        int choice;
        do {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Search Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Display Student Count");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> deleteStudent();
                case 4 -> updateStudent();
                case 5 -> searchStudent();
                case 6 -> sortStudents();
                case 7 -> displayStudentCount();
                case 8 -> exit();
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 8);

        // Save data before exiting
        saveData();
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        Student s = new Student(id, name, course);
        studentList.add(s);
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            System.out.println("\n--- Student Records ---");
            for (Student s : studentList) {
                s.display();
            }
        }
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        boolean removed = studentList.removeIf(s -> s.id == id);
        if (removed) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Student s : studentList) {
            if (s.id == id) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();
                System.out.print("Enter new course: ");
                String course = sc.nextLine();
                s.updateDetails(name, course);
                System.out.println("Student details updated.");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();
        for (Student s : studentList) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    static void sortStudents() {
        System.out.println("Sort by: 1. ID 2. Name");
        int choice = sc.nextInt();
        if (choice == 1) {
            studentList.sort(Comparator.comparingInt(s -> s.id));
        } else if (choice == 2) {
            studentList.sort(Comparator.comparing(s -> s.name));
        }
        System.out.println("Students sorted successfully.");
    }

    static void displayStudentCount() {
        System.out.println("Total Students: " + studentList.size());
    }

    static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(studentList);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            studentList = (ArrayList<Student>) in.readObject();
            System.out.println("Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found.");
        }
    }

    static void exit() {
        System.out.println("Exiting...");
    }
}

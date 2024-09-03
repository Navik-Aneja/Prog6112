package a1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementApplication {
    private ArrayList<Student> students;
    private Scanner scanner;

    public StudentManagementApplication() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentManagementApplication app = new StudentManagementApplication();
        app.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayHeader();
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                showMenu();
            } else {
                exit = true;
                System.out.println("Exiting application...");
            }
        }
    }

    private void displayHeader() {
        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("*******************************");
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new student.");
            System.out.println("(2) Search for a student.");
            System.out.println("(3) Delete a student.");
            System.out.println("(4) Print student report.");
            System.out.println("(5) Exit Application.");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Student.SaveStudent(students, scanner);
                    break;
                case "2":
                    Student.SearchStudent(students, scanner);
                    break;
                case "3":
                    Student.DeleteStudent(students, scanner);
                    break;
                case "4":
                    Student.StudentReport(students);
                    break;
                case "5":
                    Student.ExitStudentApplication();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private int age;
    private String email;
    private String course;

    public Student(String studentId, String name, int age, String email, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public static void SaveStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.println("\nCAPTURE A NEW STUDENT");
        System.out.println("**********************");
        
        System.out.print("Enter the student id: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter the student name: ");
        String name = scanner.nextLine();
        
        int age = getValidAge(scanner);
        
        System.out.print("Enter the student email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter the student course: ");
        String course = scanner.nextLine();

        Student newStudent = new Student(id, name, age, email, course);
        students.add(newStudent);

        System.out.println("Student details have been successfully saved.");
    }

    public static void SearchStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("\nEnter the student id to search: ");
        String id = scanner.nextLine();
        System.out.println("----------------------------------------");

        Student student = findStudentById(students, id);
        if (student != null) {
            student.displayDetails();
        } else {
            System.out.println("Student with Student Id: " + id + " was not found!");
        }
        
        System.out.println("----------------------------------------");
    }

    public static void DeleteStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("\nEnter the student id to delete: ");
        String id = scanner.nextLine();
        Student student = findStudentById(students, id);
        if (student != null) {
            System.out.print("Are you sure you want to delete student " + id + " from the system? Yes (y) to delete: ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                students.remove(student);
                System.out.println("----------------------------------------");
                System.out.println("Student with Student Id: " + id + " WAS deleted!");
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            System.out.println("Student with Student Id: " + id + " was not found!");
        }
    }

    public static void StudentReport(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("\nNo students available to display.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println("\nSTUDENT " + (i + 1));
                System.out.println("----------------------------------------");
                students.get(i).displayDetails();
                System.out.println("----------------------------------------");
            }
        }
    }

    public static void ExitStudentApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    private static int getValidAge(Scanner scanner) {
        while (true) {
            System.out.print("Enter the student age: ");
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age >= 16) {
                    return age;
                } else {
                    System.out.println("Invalid age. Student must be at least 16 years old.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static Student findStudentById(ArrayList<Student> students, String id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
    }
}
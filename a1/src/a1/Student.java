package a1;

import javax.swing.JOptionPane;

public class Student {
    private int ID;
    private String name;
    private int age;
    private String email;
    private String course;
    private static int capacity = 1;
    static int size = 0;
    private static boolean exit;
    public static Student[] student = new Student[capacity];

    // Constructor now properly initializes the object
    Student(String id, String name, int age, String email, String course) {
        this.ID = Integer.parseInt(id);
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    // Get the exit status
    public static boolean getexit() {
        return exit;
    }

    // Set the ID of the student
    public void setID(int ID) {
        this.ID = ID;
    }

    // Get the ID of the student
    public int getID() {
        return ID;
    }

    // Set the name of the student
    public void setName(String name) {
        this.name = name;
    }

    // Get the name of the student
    public String getName() {
        return name;
    }

    // Set the age of the student, validating it to be 16 or older
    public boolean setAge(int age) throws NumberFormatException {
        boolean validAge;

        if (age < 16) {
            // If age is less than 16, ask for a valid input
            validAge = false;
        } else {
            // If the age is valid, set it and exit the loop
            this.age = age;
            validAge = true;
        }
        return validAge;
    }

    // Get the age of the student
    public int getAge() {
        return age;
    }

    // Set the email of the student
    public void setEmail(String email) {
        this.email = email;
    }

    // Get the email of the student
    public String getEmail() {
        return email;
    }

    // Set the course of the student
    public void setCourse(String course) {
        this.course = course;
    }

    // Get the course of the student
    public String getCourse() {
        return course;
    }

    // Save a student to the array
    public static String saveStudent(Student o) {
        student[size] = o;
        size = size + 1;
        return null;
    }

    // Resize the array if it's full or too empty
    public static void resize() {
        if (2 * Student.size >= Student.capacity - 1) {
            grow();
            return;
        }

        if (Student.size < (Student.capacity / 4)) {
            shrink();
            return;
        }
    }

    // Grow the array capacity
    public static void grow() {
        capacity *= 2;
        Student[] temp = new Student[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = student[i];
        }
        student = temp;
    }

    // Shrink the array capacity
    public static void shrink() {
        capacity /= 2;
        Student[] temp = new Student[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = student[i];
        }
        student = temp;
    }

    // Search for a student by ID
    public String searchID(int ID) {
        String message = "Student not found";

        for (int i = 0; i < size; i++) {
            if (student[i].getID() == ID) {
                message = "Student found:\n" +
                        "Name: " + student[i].getName() + "\n" +
                        "Age: " + student[i].getAge() + "\n" +
                        "ID: " + student[i].getID() + "\n" +
                        "Email: " + student[i].getEmail() + "\n" +
                        "Course: " + student[i].getCourse();
                JOptionPane.showMessageDialog(null, message);
                return message;
            }
        }

        JOptionPane.showMessageDialog(null, message);
        return message;
    }

    // Get the index of a student by ID
    public static int getIndexID(int ID) {
        for (int i = 0; i < size; i++) {
            if (student[i].getID() == ID) {
                return i;
            }
        }
        return -1;
    }

     // Remove a student by index
    public static Student remove(int idx) {
        if (idx < 0 || idx >= size) {
            return null;
        }
        Student temp = student[idx];
        for (int i = idx; i < size - 1; i++) {
            student[i] = student[i + 1];
        }
        student[size - 1] = null;
        size--;
        resize();
        return temp;
    }

    // Remove a student by ID
    public static Student removeByID(int ID) {
        int idx = getIndexID(ID);
        if (idx == -1) {
            JOptionPane.showMessageDialog(null, "Student " + ID + " was not found");
            return null;
        }
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the student with ID " + ID + "?", "Confirm Removal", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.NO_OPTION) {
            return null;
        } else {
            Student removed = remove(idx);
            JOptionPane.showMessageDialog(null, "Student " + ID + " was removed successfully");
            return removed;
        }
    }
    // Display all students
    public static String displayStudents() {
        StringBuilder storage = new StringBuilder();  // StringBuilder to build the display string

        // Append details of all students to the string
        for (int i = 0; i < size; i++) {
            storage.append("Student ").append(i + 1).append(":\n")
                    .append("  Name: ").append(student[i].getName()).append("\n")
                    .append("  Age: ").append(student[i].getAge()).append("\n")
                    .append("  ID: ").append(student[i].getID()).append("\n")
                    .append("  Email: ").append(student[i].getEmail()).append("\n")
                    .append("  Course: ").append(student[i].getCourse()).append("\n")
                    .append("\n");
        }

        // Display information about all students
        JOptionPane.showMessageDialog(null, storage.toString());
        return storage.toString();
    }

    // Exit the application based on user input
    public static boolean exitApplication() {
        String choice = JOptionPane.showInputDialog(null, "Enter '1' to show the pop-up menu and any other key to exit the application");

        if (!"1".equals(choice)) {
            return exit = true;  // Exit the application
        }

        return exit = false;  // Do not exit, show the pop-up menu
    }
}
    
    
    


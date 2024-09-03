package s2;

import java.util.ArrayList;
import java.util.Scanner;

public class AdoptionAgencyApp {
    public static void main(String[] args) {
        AdoptionAgency agency = new AdoptionAgency();
        agency.run();
    }
}

class AdoptionAgency {
    private ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<Adopter> adopters = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    addAdopter();
                    break;
                case 3:
                    listPets();
                    break;
                case 4:
                    listAdopters();
                    break;
                case 5:
                    matchPetWithAdopter();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Adoption Agency Menu ---");
        System.out.println("1. Add a new pet");
        System.out.println("2. Add a new adopter");
        System.out.println("3. List all pets");
        System.out.println("4. List all adopters");
        System.out.println("5. Match pet with adopter");
        System.out.println("6. Generate report");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addPet() {
        System.out.print("Enter pet type (Dog/Cat): ");
        String type = scanner.nextLine();
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Pet pet;
        if (type.equalsIgnoreCase("Dog")) {
            pet = new Dog(name, age);
        } else if (type.equalsIgnoreCase("Cat")) {
            pet = new Cat(name, age);
        } else {
            System.out.println("Invalid pet type. Only Dogs and Cats are accepted.");
            return;
        }

        pets.add(pet);
        System.out.println("Pet added successfully!");
    }

    private void addAdopter() {
        System.out.print("Enter adopter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter adopter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter preferred pet type (Dog/Cat/Any): ");
        String preferredPetType = scanner.nextLine();

        Adopter adopter = new Adopter(name, age, preferredPetType);
        adopters.add(adopter);
        System.out.println("Adopter added successfully!");
    }

    private void listPets() {
        System.out.println("\n--- List of Pets ---");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    private void listAdopters() {
        System.out.println("\n--- List of Adopters ---");
        for (Adopter adopter : adopters) {
            System.out.println(adopter);
        }
    }

    private void matchPetWithAdopter() {
        System.out.print("Enter adopter name: ");
        String adopterName = scanner.nextLine();
        Adopter adopter = findAdopter(adopterName);

        if (adopter == null) {
            System.out.println("Adopter not found.");
            return;
        }

        Pet matchedPet = null;
        for (Pet pet : pets) {
            if (adopter.getPreferredPetType().equalsIgnoreCase("Any") ||
                adopter.getPreferredPetType().equalsIgnoreCase(pet.getClass().getSimpleName())) {
                matchedPet = pet;
                break;
            }
        }

        if (matchedPet != null) {
            System.out.println("Match found!");
            System.out.println("Adopter: " + adopter);
            System.out.println("Pet: " + matchedPet);
            pets.remove(matchedPet);
        } else {
            System.out.println("No matching pet found for the adopter's preference.");
        }
    }

    private Adopter findAdopter(String name) {
        for (Adopter adopter : adopters) {
            if (adopter.getName().equalsIgnoreCase(name)) {
                return adopter;
            }
        }
        return null;
    }

    private void generateReport() {
        System.out.println("\n--- Adoption Agency Report ---");
        System.out.println("Total Pets: " + pets.size());
        System.out.println("Total Adopters: " + adopters.size());
        
        int dogs = 0, cats = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) dogs++;
            else if (pet instanceof Cat) cats++;
        }
        
        System.out.println("Dogs available: " + dogs);
        System.out.println("Cats available: " + cats);
        
        System.out.println("\nAdopter Preferences:");
        int dogPreference = 0, catPreference = 0, anyPreference = 0;
        for (Adopter adopter : adopters) {
            switch (adopter.getPreferredPetType().toLowerCase()) {
                case "dog": dogPreference++; break;
                case "cat": catPreference++; break;
                case "any": anyPreference++; break;
            }
        }
        System.out.println("Dog preference: " + dogPreference);
        System.out.println("Cat preference: " + catPreference);
        System.out.println("Any pet preference: " + anyPreference);
    }
}

abstract class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " - Name: " + name + ", Age: " + age;
    }
}

class Dog extends Pet {
    public Dog(String name, int age) {
        super(name, age);
    }
}

class Cat extends Pet {
    public Cat(String name, int age) {
        super(name, age);
    }
}

class Adopter {
    private String name;
    private int age;
    private String preferredPetType;

    public Adopter(String name, int age, String preferredPetType) {
        this.name = name;
        this.age = age;
        this.preferredPetType = preferredPetType;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPreferredPetType() { return preferredPetType; }

    @Override
    public String toString() {
        return "Adopter - Name: " + name + ", Age: " + age + ", Preferred Pet: " + preferredPetType;
    }
}
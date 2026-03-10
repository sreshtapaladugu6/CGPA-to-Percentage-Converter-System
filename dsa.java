import java.util.ArrayList;
import java.util.Scanner;

/**
 * CGPA Calculator using Data Structures and Algorithms
 * This program demonstrates CGPA calculation with proper data structures
 */

class Subject {
    private String name;
    private double grade;
    private int credits;

    // Constructor
    public Subject(String name, double grade, int credits) {
        this.name = name;
        this.grade = grade;
        this.credits = credits;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public int getCredits() {
        return credits;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Subject: " + name + ", Grade: " + grade + ", Credits: " + credits;
    }
}

class CGPACalculator {
    private ArrayList<Subject> subjects;

    // Constructor
    public CGPACalculator() {
        this.subjects = new ArrayList<>();
    }

    // Add a subject
    public void addSubject(String name, double grade, int credits) {
        if (grade < 0 || grade > 10) {
            System.out.println("Error: Grade must be between 0 and 10");
            return;
        }
        if (credits < 1) {
            System.out.println("Error: Credits must be at least 1");
            return;
        }
        subjects.add(new Subject(name, grade, credits));
    }

    // Remove a subject by index
    public void removeSubject(int index) {
        if (index >= 0 && index < subjects.size()) {
            subjects.remove(index);
            System.out.println("Subject removed successfully");
        } else {
            System.out.println("Error: Invalid subject index");
        }
    }

    // Calculate CGPA
    public double calculateCGPA() {
        if (subjects.isEmpty()) {
            System.out.println("Error: No subjects added");
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Subject subject : subjects) {
            totalPoints += subject.getGrade() * subject.getCredits();
            totalCredits += subject.getCredits();
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalPoints / totalCredits;
    }

    // Convert CGPA to Percentage
    public double cgpaToPercentage(double cgpa) {
        if (cgpa < 0 || cgpa > 10) {
            System.out.println("Error: CGPA must be between 0 and 10");
            return 0.0;
        }
        return cgpa * 9.5;
    }

    // Convert Percentage to CGPA
    public double percentageToCGPA(double percentage) {
        if (percentage < 0 || percentage > 100) {
            System.out.println("Error: Percentage must be between 0 and 100");
            return 0.0;
        }
        return percentage / 9.5;
    }

    // Display all subjects
    public void displaySubjects() {
        if (subjects.isEmpty()) {
            System.out.println("No subjects added yet.");
            return;
        }

        System.out.println("\n===== All Subjects =====");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }
        System.out.println("========================\n");
    }

    // Get number of subjects
    public int getSubjectCount() {
        return subjects.size();
    }

    // Clear all subjects
    public void clearAllSubjects() {
        subjects.clear();
        System.out.println("All subjects cleared");
    }

    // Get grade letter from grade points
    public String getGradeLetter(double grade) {
        if (grade >= 10.0) return "O (Outstanding)";
        else if (grade >= 9.0) return "A+ (Excellent)";
        else if (grade >= 8.0) return "A (Very Good)";
        else if (grade >= 7.0) return "B+ (Good)";
        else if (grade >= 6.0) return "B (Average)";
        else if (grade >= 5.0) return "C (Below Average)";
        else return "F (Fail)";
    }
}

public class dsa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CGPACalculator calculator = new CGPACalculator();
        boolean running = true;

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   CGPA to Percentage Conversion Calculator ║");
        System.out.println("╚════════════════════════════════════════════╝\n");

        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Calculate CGPA from Subjects");
            System.out.println("2. Convert CGPA to Percentage");
            System.out.println("3. Convert Percentage to CGPA");
            System.out.println("4. View All Subjects");
            System.out.println("5. Remove a Subject");
            System.out.println("6. Clear All Subjects");
            System.out.println("7. About CGPA Conversion");
            System.out.println("8. Exit");
            System.out.println("===============================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Calculate CGPA from subjects
                    System.out.println("\n--- Calculate CGPA ---");
                    System.out.print("How many subjects do you want to add? ");
                    int numSubjects = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numSubjects; i++) {
                        System.out.println("\nSubject " + (i + 1) + ":");
                        System.out.print("Enter subject name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter grade points (0-10): ");
                        double grade = scanner.nextDouble();

                        System.out.print("Enter credits: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();

                        calculator.addSubject(name, grade, credits);
                    }

                    double cgpa = calculator.calculateCGPA();
                    if (cgpa > 0) {
                        System.out.println("\n✓ Your CGPA: " + String.format("%.2f", cgpa));
                        System.out.println("✓ Equivalent Percentage: " + String.format("%.2f", calculator.cgpaToPercentage(cgpa)) + "%");
                    }
                    break;

                case 2:
                    // CGPA to Percentage
                    System.out.println("\n--- CGPA to Percentage ---");
                    System.out.print("Enter CGPA (0-10): ");
                    double inputCGPA = scanner.nextDouble();
                    double percentage = calculator.cgpaToPercentage(inputCGPA);
                    if (percentage > 0) {
                        System.out.println("✓ Percentage: " + String.format("%.2f", percentage) + "%");
                    }
                    break;

                case 3:
                    // Percentage to CGPA
                    System.out.println("\n--- Percentage to CGPA ---");
                    System.out.print("Enter Percentage (0-100): ");
                    double inputPercentage = scanner.nextDouble();
                    double resultCGPA = calculator.percentageToCGPA(inputPercentage);
                    if (resultCGPA > 0) {
                        System.out.println("✓ CGPA: " + String.format("%.2f", resultCGPA));
                    }
                    break;

                case 4:
                    // View all subjects
                    calculator.displaySubjects();
                    if (calculator.getSubjectCount() > 0) {
                        System.out.println("Current CGPA: " + String.format("%.2f", calculator.calculateCGPA()));
                    }
                    break;

                case 5:
                    // Remove a subject
                    calculator.displaySubjects();
                    if (calculator.getSubjectCount() > 0) {
                        System.out.print("Enter subject number to remove: ");
                        int removeIndex = scanner.nextInt() - 1;
                        calculator.removeSubject(removeIndex);
                    }
                    break;

                case 6:
                    // Clear all subjects
                    calculator.clearAllSubjects();
                    break;

                case 7:
                    // About CGPA
                    displayAboutInfo();
                    break;

                case 8:
                    // Exit
                    System.out.println("\nThank you for using CGPA Calculator!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    public static void displayAboutInfo() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         About CGPA Conversion              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        System.out.println("\n📚 What is CGPA?");
        System.out.println("CGPA (Cumulative Grade Point Average) is a grading system");
        System.out.println("used by educational institutions to evaluate overall");
        System.out.println("academic performance.");

        System.out.println("\n📐 Conversion Formula:");
        System.out.println("Percentage = CGPA × 9.5");
        System.out.println("CGPA = Percentage ÷ 9.5");

        System.out.println("\n📊 CGPA to Percentage Scale:");
        System.out.println("10.0 CGPA = 95.0%");
        System.out.println("9.5 CGPA  = 90.25%");
        System.out.println("9.0 CGPA  = 85.5%");
        System.out.println("8.5 CGPA  = 80.75%");
        System.out.println("8.0 CGPA  = 76.0%");
        System.out.println("7.5 CGPA  = 71.25%");
        System.out.println("7.0 CGPA  = 66.5%");

        System.out.println("\n🎓 Grade Point Scale:");
        System.out.println("O  (Outstanding) = 10 points");
        System.out.println("A+ (Excellent)   = 9 points");
        System.out.println("A  (Very Good)   = 8 points");
        System.out.println("B+ (Good)        = 7 points");
        System.out.println("B  (Average)     = 6 points");
        System.out.println("C  (Below Avg)   = 5 points");

        System.out.println("\n⚠️  Important Note:");
        System.out.println("Different institutions may use different conversion formulas.");
        System.out.println("This calculator uses the most commonly accepted formula (×9.5).");
        System.out.println("Always check with your institution for their official method.\n");
    }
}

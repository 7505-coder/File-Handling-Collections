package app;

import model.Student;
import service.StudentManager;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // data file relative to repo root (when run locally, adjust path)
        String dataPath = "data/students.txt";
        StudentManager manager = new StudentManager(dataPath);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks (desc)");
            System.out.println("6. Read file at offset (demo)");
            System.out.println("7. Save and Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            try {
                int ch = Integer.parseInt(choice);
                switch (ch) {
                    case 1:
                        System.out.print("Enter Roll No: "); int r = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Name: "); String n = sc.nextLine();
                        System.out.print("Enter Email: "); String e = sc.nextLine();
                        System.out.print("Enter Course: "); String c = sc.nextLine();
                        System.out.print("Enter Marks: "); double m = Double.parseDouble(sc.nextLine());
                        Student s = new Student(r, n, e, c, m);
                        manager.addStudent(s);
                        break;
                    case 2:
                        manager.viewAll();
                        break;
                    case 3:
                        System.out.print("Enter Name to search: "); String sn = sc.nextLine();
                        Student found = manager.searchByName(sn);
                        if (found != null) found.display();
                        else System.out.println("Not found.");
                        break;
                    case 4:
                        System.out.print("Enter Name to delete: "); String dn = sc.nextLine();
                        boolean del = manager.deleteByName(dn);
                        System.out.println(del ? "Deleted." : "Name not found.");
                        break;
                    case 5:
                        manager.sortByMarksDesc();
                        System.out.println("Sorted Student List by Marks:");
                        manager.viewAll();
                        break;
                    case 6:
                        System.out.print("Enter byte offset to read: ");
                        long offset = Long.parseLong(sc.nextLine());
                        String line = manager.readAtOffset(offset);
                        System.out.println("Read at offset: " + line);
                        break;
                    case 7:
                        manager.saveAndExit();
                        System.out.println("Saved to file. Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid numeric input.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}

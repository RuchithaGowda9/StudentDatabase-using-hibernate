package com.crimsonogic.hiberateusingannotations.entity;

import java.util.Scanner;
import com.crimsonogic.hiberateusingannotations.operations.HibernateOperations;

public class HibernateMain {
    public static void main(String[] args) {
        HibernateOperations operations = new HibernateOperations();
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        char ch = 'y';

        while (ch != 'n') {
            System.out.println("-------------------------------------------");
            System.out.println("Hibernate Operations Menu:");
            System.out.println("insert : Insert Student");
            System.out.println("deleteall : Delete All Students");
            System.out.println("delete : Delete Student by ID");
            System.out.println("display : Display All Students");
            System.out.println("exit : Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.next();

            switch (choice) {
                case "insert":
                    operations.insertIntoStudent();
                    break;
                case "deleteall":
                    operations.deleteAllFromStudent();
                    break;
                case "delete":
                    System.out.print("Enter Student ID to delete: ");
                    int id = scanner.nextInt();
                    operations.deleteUsingId(id);
                    break;
                case "display":
                    operations.displayAllStudents();
                    break;
                case "exit":
                    System.out.println("Exited");
                    ch = 'n'; // Set ch to 'n' to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            if (!choice.equals("exit")) {
                System.out.print("Do you want to continue (y/n)? ");
                ch = scanner.next().charAt(0);
            }
        }

        scanner.close();
    }
}

package org.example.controller;

import org.example.service.BookService;
import org.example.service.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentController {
    public static void studentMenu(){
        boolean working = true;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String option ;
        while(working){
            System.out.println("________________________________");
            System.out.println("[1] Add a student");
            System.out.println("[2] Update a student");
            System.out.println("[3] Remove a student");
            System.out.println("[4] Return a book");
            System.out.println("[5] Show all students");
            System.out.println("[6] Show all that borrowed");
            System.out.println("[7] Show all that not borrowed");
            System.out.println("[0] Close");
            System.out.println("________________________________");
            try {
                option = scanner.nextLine();
                choice = Integer.parseInt(option);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Invalid choice");
            }finally {
                if (choice == 0){
                    working = false;
                } else if (choice == 1) {
                    StudentService.addStudent();
                } else if ( choice == 2) {
                    StudentService.updateStudent();
                } else if (choice == 3) {
                    StudentService.deleteById();
                } else if (choice == 4) {
                    StudentService.returnBookToLibrary();
                } else if (choice == 5) {
                    StudentService.printAllStudents();
                } else if (choice == 6) {
                    StudentService.printBorrowedOnly();
                } else if (choice == 7) {
                    StudentService.printNotBorrowed();
                }else {
                    System.out.println("Invalid Option");
                }
            }
        }
    }
}

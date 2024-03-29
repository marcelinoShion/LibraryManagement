package org.example.controller;

import org.example.service.BookService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookController {
    public static void bookMenu(){
        boolean working = true;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String option;
        while(working){
            System.out.println("________________________________");
            System.out.println("[1] Add a book");
            System.out.println("[2] Update a book");
            System.out.println("[3] Remove a book");
            System.out.println("[4] Borrow a book");
            System.out.println("[5] Show all books");
            System.out.println("[6] Show all lended books");
            System.out.println("[7] Show all not lended books");
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
                    BookService.addNewBook();
                } else if ( choice == 2) {
                    BookService.updateBook();
                } else if (choice == 3) {
                    BookService.removeBook();
                } else if (choice == 4) {
                    BookService.borrowABook();
                } else if (choice == 5) {
                    BookService.printAllBooks();
                } else if (choice == 6) {
                    BookService.printAllLendedBooks();
                } else if (choice == 7) {
                    BookService.printNotLendedBooks();
                }else {
                    System.out.println("Invalid Option");
                }
            }
        }
    }
}

package org.example.controller;

import org.example.service.BookService;
import org.example.service.StatisticsService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StatisticController {
    public static void StatisticMenu(){
        boolean working = true;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String option ;
        while(working){
            System.out.println("________________________________");
            System.out.println("[1] Total number of books");
            System.out.println("[2] Total number of lended books");
            System.out.println("[3] Total number of free books");
            System.out.println("[4] Total number of students");
            System.out.println("[5] Total number of students that borrowed");
            System.out.println("[6] Total number of students that not borrowed");
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
                    StatisticsService.numberOfBooks();
                } else if ( choice == 2) {
                    StatisticsService.numberOfLendedBooks();
                } else if (choice == 3) {
                    StatisticsService.numberOfFreeBooks();
                } else if (choice == 4) {
                    StatisticsService.numberOfAllStudents();
                } else if (choice == 5) {
                    StatisticsService.numberOfStudentsThatBorrowed();
                } else if (choice == 6) {
                    StatisticsService.numberOfStudentsThatNotBorrowed();
                } else {
                    System.out.println("Invalid Option");
                }
            }
        }
    }
}

package org.example;


import org.example.controller.BookController;
import org.example.controller.StudentController;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        boolean working = true;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        String option;
        while(working){
            System.out.println("________________________________");
            System.out.println("[1] Student Menu");
            System.out.println("[2] Book Menu");
            System.out.println("[0] Close");
            System.out.println("________________________________");

            try{
                option = scanner.nextLine();
                choice = Integer.parseInt(option);
            }catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("Invalid choice");
            }finally {
                if(choice == 0){
                    working = false;
                } else if (choice == 1) {
                    StudentController.studentMenu();
                } else if (choice == 2) {
                        BookController.bookMenu();
                } else {
                    System.out.println("Invalid number");
                }
            }
        }
    }
}
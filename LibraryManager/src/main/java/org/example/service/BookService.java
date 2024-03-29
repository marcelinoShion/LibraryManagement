package org.example.service;

import org.example.entities.Book;
import org.example.entities.Student;
import org.example.repository.BookRepository;
import org.example.repository.ShowBooksRepository;
import org.example.repository.ShowStudentsRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookService {
    private static Scanner scanner = new Scanner(System.in);
    public static void addNewBook(){
        System.out.println("Type a book name");
        try {
            String name =  scanner.nextLine();
            Book book = new Book(name);
            BookRepository.insertBookInDatabase(book);
        } catch (InputMismatchException e){
            e.printStackTrace();
        }

    }
    public static void removeBook(){
        try{
            System.out.println("Enter the Id of the book you want to remove");
            Integer id = scanner.nextInt();
            BookRepository.deleteBookFromDatabase(id);
        } catch (InputMismatchException e){
            e.printStackTrace();
        }

    }
    public static void updateBook(){
        try {
            System.out.println("Enter the Id you want to change");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the name of the Book");
            String name = scanner.nextLine();
            Book book = new Book(id,name);

            BookRepository.updateBookName(book);
        } catch (InputMismatchException e){
            e.printStackTrace();
        }

    }
    public static void borrowABook(){
        try {
            System.out.println("Enter Id of the student that wish to borrow");
            Integer id = scanner.nextInt();
            Optional<Student> optionalStudent = Optional.ofNullable(ShowStudentsRepository.findStudentById(id));
            if (optionalStudent.isEmpty() ){
                System.out.println("No Student was found");
            }else {
                if (optionalStudent.get().getBook_id() != null){
                    System.out.println("You need to return the book");
                }else {
                    System.out.println("Enter the book name that you want to borrow");
                    scanner.nextLine();
                    String name =  scanner.nextLine();
                    BookRepository.lendTheBookToStudent(optionalStudent.get(),name);
                }
            }
        }catch (InputMismatchException e){
            e.printStackTrace();
        }

    }
    public static void printAllBooks(){
        Optional<List<Book>> optionalBookList = Optional.ofNullable(ShowBooksRepository.showAllBooks());
        if(optionalBookList.isPresent()){
            for(var book : optionalBookList.get()){
                System.out.println("Id = %d | Name = %s | Student id = %d".formatted(book.getId(),book.getName(),book.getStudent_id()));
            }
        }else {
            System.out.println("There is no books");
        }

    }
    public static void printAllLendedBooks(){
        Optional<List<Book>> optionalBookList = Optional.ofNullable(ShowBooksRepository.showLendedOnly());
        if(optionalBookList.isPresent()){
            for(var book : optionalBookList.get()){
                System.out.println("Id = %d | Name = %s | Student id = %d".formatted(book.getId(),book.getName(),book.getStudent_id()));
            }
        }else {
            System.out.println("No book was lended");
        }

    }
    public static void printNotLendedBooks(){
        Optional<List<Book>> optionalBookList = Optional.ofNullable(ShowBooksRepository.showNotLendedBooks());
        if(optionalBookList.isPresent()){
            for(var book : optionalBookList.get()){
                System.out.println("Id = %d | Name = %s | Student id = %d".formatted(book.getId(),book.getName(),book.getStudent_id()));
            }
        }else {
            System.out.println("All books was lended");
        }

    }

}

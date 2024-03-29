package org.example.service;

import org.example.entities.Book;
import org.example.entities.Student;
import org.example.repository.ShowBooksRepository;
import org.example.repository.ShowStudentsRepository;

import java.util.List;
import java.util.Optional;

public class StatisticsService {
    public static void numberOfBooks(){
        System.out.println("The number of books is %d "
                .formatted(ShowBooksRepository.showAllBooks().size()));
    }
    public static void numberOfLendedBooks(){
        Optional<List<Book>> optionalBookList = Optional.ofNullable(ShowBooksRepository.showLendedOnly());
        if (optionalBookList.isPresent()){
            System.out.println("The number of lended books is %d of %d".formatted(
                    optionalBookList.get().size(), ShowBooksRepository.showAllBooks().size()));
        }else {
            System.out.println("The number of lended books is 0 of %d".formatted(
                    ShowBooksRepository.showAllBooks().size()));
        }

    }
    public static void numberOfFreeBooks() {
        Optional<List<Book>> optionalBookList = Optional.ofNullable(ShowBooksRepository.showNotLendedBooks());
        if (optionalBookList.isPresent()) {
            System.out.println("The number of free books is %d of %d".formatted(optionalBookList.get().size(),
                    ShowBooksRepository.showAllBooks().size()));
        } else {
            System.out.println("The number of free books is 0 of %d".formatted(ShowBooksRepository.showAllBooks().size()));
        }
    }
    public static void numberOfAllStudents(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showAllStudents());
        if (optionalStudentList.isPresent()) {
            System.out.println("The number of students is %d".formatted(optionalStudentList.get().size()));
        } else {
            System.out.println("The number of students is 0");
        }

    }
    public static void numberOfStudentsThatBorrowed(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showBorrowedOnly());
        Optional<List<Student>> optionalStudent = Optional.ofNullable(ShowStudentsRepository.showAllStudents());
        if (optionalStudentList.isPresent()) {
            System.out.println("The number of students that borrowed is %d of %d".formatted(
                    optionalStudentList.get().size(),optionalStudent.get().size()));
        } else {
            System.out.println("The number of students that borrowed is 0");
        }
    }
    public static void numberOfStudentsThatNotBorrowed(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showNotBorrowedBooks());
        Optional<List<Student>> optionalStudent = Optional.ofNullable(ShowStudentsRepository.showAllStudents());
        if (optionalStudentList.isPresent()) {
            System.out.println("The number of students that not borrowed is %d of %d".formatted(
                    optionalStudentList.get().size(),optionalStudent.get().size()));
        } else {
            System.out.println("The number students that not borrowed is 0");
        }
    }

}

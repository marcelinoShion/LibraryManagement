package org.example.service;

import org.example.entities.Student;
import org.example.repository.ShowStudentsRepository;
import org.example.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentService {
    private static Scanner scanner = new Scanner(System.in) ;

    public static void addStudent(){
        System.out.println("Enter the name of the student");
        String name = scanner.nextLine();
        System.out.println("Enter the age of student");
        int age = scanner.nextInt();
        Student student = new Student(name,age);
        StudentRepository.insertStudentInDatabase(student);
    }
    public static void deleteById(){
        System.out.println("Enter the Id of the student that you want remove");
        Integer id = scanner.nextInt();
        StudentRepository.deleteStudentInDatabase(id);
    }
    public static void updateStudent(){
        System.out.println("Enter with the id of the student that you want to update");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name");
        String name = scanner.nextLine();
        System.out.println("Enter the age");
        int age = scanner.nextInt();
        Student student = new Student(id,name,age);
        StudentRepository.updateStudentInDatabase(student);
    }
    public static void returnBookToLibrary(){
        System.out.println("Enter the Id of the student that is returing the book");
        Integer id = scanner.nextInt();

        Optional<Student> optionalStudent = Optional.ofNullable(ShowStudentsRepository.findStudentById(id));
        if (optionalStudent.isPresent()){
            if (optionalStudent.get().getBook_id() != null){
                StudentRepository.returnBook(optionalStudent.get());
            }else {
                System.out.println("The student didn't lend a book");
            }
        }else {
            System.out.println("Student not found");
        }
    }
    public static void printAllStudents(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showAllStudents());
        if(optionalStudentList.isPresent()){
            List<Student>studentList = optionalStudentList.get();
            for (var student : studentList){
                System.out.println("Id = %d  |Name = %s |Age = %d |Book Id = %d".formatted(student.getId(),
                        student.getName(),student.getAge(),student.getBook_id()));
            }
        }else {
            System.out.println("No student was registered ");
        }

    }
    public static void printBorrowedOnly(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showBorrowedOnly());
        if(optionalStudentList.isPresent()){
            List<Student>studentList = optionalStudentList.get();
            for (var student : studentList){
                System.out.println("Id = %d  |Name = %s |Age = %d |Book Id = %d".formatted(student.getId(),
                        student.getName(),student.getAge(),student.getBook_id()));
            }
        }else {
            System.out.println("No student have borrowed ");
        }
    }
    public static void printNotBorrowed(){
        Optional<List<Student>> optionalStudentList = Optional.ofNullable(ShowStudentsRepository.showNotBorrowedBooks());
        if(optionalStudentList.isPresent()){
            List<Student>studentList = optionalStudentList.get();
            for (var student : studentList){
                System.out.println("Id = %d  |Name = %s |Age = %d |Book Id = %d".formatted(student.getId(),
                        student.getName(),student.getAge(),student.getBook_id()));
            }
        }else {
            System.out.println("No student was found ");
        }
    }
}

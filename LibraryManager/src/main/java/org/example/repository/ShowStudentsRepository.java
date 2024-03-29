package org.example.repository;

import org.example.DBConnection.DbConnector;
import org.example.entities.Book;
import org.example.entities.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentsRepository {
    public static List<Student> showAllStudents(){
        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, age, book_id\n" +
                "\tFROM public.\"Students\";";

        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()){
                Student student= new Student(resultSet.getInt("id"),resultSet.getString("name"),
                        resultSet.getInt("age")
                        , resultSet.getObject("book_id") == null ? null : resultSet.getInt("book_id") );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.size() >= 1 ? students : null;
    }
    public static List<Student> showBorrowedOnly(){
        List<Student> students  = new ArrayList<>();
        String sql = "SELECT id, name, age, book_id\n" +
                "\tFROM public.\"Students\" where book_id is not null;";
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Student student = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getInt("book_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.size() >= 1 ? students : null;
    }
    public static List<Student> showNotBorrowedBooks(){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, age, book_id\n" +
                "\tFROM public.\"Students\" where book_id is null;";
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Student student = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getObject("book_id") == null ? null : resultSet.getInt("student_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.size() >= 1 ? students : null;
    }
    public static Student findStudentById(Integer id){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, age, book_id\n" +
                "\tFROM public.\"Students\" where id = %d;".formatted(id);
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Student student = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getObject("book_id") == null ? null : resultSet.getInt("book_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.size() >= 1 ? students.get(0) : null;
    }
}

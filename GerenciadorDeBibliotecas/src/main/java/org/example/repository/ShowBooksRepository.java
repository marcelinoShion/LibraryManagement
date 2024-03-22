package org.example.repository;

import org.example.DBConnection.DbConnector;
import org.example.entities.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowBooksRepository {
    public static List<Book> showAllBooks(){
        List<Book> books = new ArrayList<>();

        String sql = "SELECT id, name, student_id\n" +
                "\tFROM public.\"Books\";";

        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()){
                Book book = new Book(resultSet.getInt("id"),resultSet.getString("name")
                        , resultSet.getObject("student_id") == null ? null : resultSet.getInt("student_id") );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static List<Book> showLendedOnly(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, name, student_id\n" +
                "\tFROM public.\"Books\" where student_id is not null  ;";
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Book book = new Book(resultSet.getInt("id"),
                                     resultSet.getString("name"),
                                     resultSet.getInt("student_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books.size() >= 1 ? books : null;
    }
    public static List<Book> showNotLendedBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, name, student_id\n" +
                "\tFROM public.\"Books\" where student_id is null  ;";
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getObject("student_id") == null ? null : resultSet.getInt("student_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books.size() >= 1 ? books : null;
    }
}

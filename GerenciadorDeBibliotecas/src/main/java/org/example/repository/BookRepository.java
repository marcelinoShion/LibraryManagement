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
import java.util.Optional;

public class BookRepository {
    public static void insertBookInDatabase(Book book){
        String sql = "INSERT INTO public.\"Books\"( name) VALUES ( '%s');"
                .formatted(book.getName());
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteBookFromDatabase(int id){
        String sql = "DELETE FROM public.\"Books\"\n" +
                "\tWHERE id = %d;".formatted(id);
        String sql2 = "UPDATE public.\"Students\"\n" +
                "\tSET  book_id= null WHERE  book_id= %d ;".formatted(id);
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateBookName(Book book){
        String sql = "UPDATE public.\"Books\"\n" +
                "\tSET  name='%s' WHERE id =%d;".formatted(book.getName(),book.getId());
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Book findABookToLend(String name){
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT id, name, student_id\n" +
                "\tFROM public.\"Books\" where student_id is null and name = '%s';".formatted(name);
        try(Connection connection = DbConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()){
                Book book = new Book(resultSet.getInt("id"),resultSet.getString("name")
                        ,resultSet.getInt("student_id"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList.size() >= 1 ? bookList.get(0) : null;
    }

    public  static void lendTheBookToStudent(Student student,String name){
        Optional<Book> optionalBook= Optional.ofNullable(findABookToLend(name));
        if (optionalBook.isPresent()){
            Book book1 = optionalBook.get();
            String sql1 = "UPDATE public.\"Books\"\n" +
                    "\tSET  student_id = %d WHERE id = %d;".formatted(student.getId(),book1.getId());
            String sql2 = "UPDATE public.\"Students\"\n" +
                    "\tSET  book_id= %d WHERE  id = %d ;".formatted(book1.getId(),student.getId());
            try(Connection connection = DbConnector.getConnection();
                Statement statement = connection.createStatement();
                ) {
                statement.executeUpdate(sql1);
                statement.executeUpdate(sql2);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Book not found");
        }
    }
}

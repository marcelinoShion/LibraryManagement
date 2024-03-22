package org.example.repository;

import org.example.DBConnection.DbConnector;
import org.example.entities.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {
        public static void insertStudentInDatabase(Student student){
                String sql = "INSERT INTO public.\"Students\"(name, age)\n" +
                        "\tVALUES ('%s', %d);".formatted(student.getName(),student.getAge());
                try(Connection connection = DbConnector.getConnection();
                    Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        public static void deleteStudentInDatabase(Integer Id){
                String sql = "DELETE FROM public.\"Students\"\n" +
                        "\tWHERE  id = %d;".formatted(Id);
                try(Connection connection = DbConnector.getConnection();
                    Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
        public static void updateStudentInDatabase(Student student){
                String sql = "UPDATE public.\"Students\"\n" +
                        "\tSET  name= '%s', age=%d WHERE id = %d ;".formatted(student.getName(),student.getAge(),student.getId());
                try(Connection connection = DbConnector.getConnection();
                    Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }


        public  static  void returnBook(Student student){
                String sql1 = "UPDATE public.\"Books\"\n" +
                        "\tSET  student_id= null\n" +
                        "\tWHERE id = %d;".formatted(student.getBook_id());
                String sql2 = "UPDATE public.\"Students\"\n" +
                        "\tSET  book_id= null\n" +
                        "\tWHERE id = %d ;".formatted(student.getId());
                try(Connection connection = DbConnector.getConnection();
                    Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql1);
                        statement.executeUpdate(sql2);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

}

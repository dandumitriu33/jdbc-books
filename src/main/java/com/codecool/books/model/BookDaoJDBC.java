package com.codecool.books.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements BookDao {
    private DataSource dataSource;

    public BookDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Book book) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "INSERT INTO book (title, author_id) VALUES ('"+ book.getTitle() +"', "+ book.getAuthor().getId() +")";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }





    }

    @Override
    public void update(Book book) {

    }

    @Override
    public Book get(int id) {
        Connection conn = null;
        Statement stmt = null;
        Book tempBook = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT book.id as bookId, title as bookTitle, first_name as authorFirst, last_name as authorLast, birth_date as authorBirthDate, author_id as authorId FROM book JOIN author author on book.author_id = author.id";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int bookIdFromDB = rs.getInt("bookId");

                if (bookIdFromDB == id) {
                    String title = rs.getString("bookTitle");
                    String first = rs.getString("authorFirst");
                    String last = rs.getString("authorLast");
                    Date birthDate = rs.getDate( "authorBirthDate");
                    int authorId = rs.getInt("authorId");

                    Author tempAuthor = new Author(first, last, birthDate);
                    tempAuthor.setId(authorId);
                    tempBook = new Book(tempAuthor, title);
                    tempBook.setId(bookIdFromDB);
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return tempBook;
    }

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT book.id as bookId, title as bookTitle, first_name as authorFirst, last_name as authorLast, birth_date as authorBirthDate, author_id as authorId FROM book JOIN author author on book.author_id = author.id";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int bookId = rs.getInt("bookId");
                String title = rs.getString("bookTitle");
                String first = rs.getString("authorFirst");
                String last = rs.getString("authorLast");
                Date birthDate = rs.getDate( "authorBirthDate");
                int authorId = rs.getInt("authorId");

                Author tempAuthor = new Author(first, last, birthDate);
                tempAuthor.setId(authorId);
                Book tempBook = new Book(tempAuthor, title);
                tempBook.setId(bookId);
                result.add(tempBook);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(
        SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

        return result;
    }
}

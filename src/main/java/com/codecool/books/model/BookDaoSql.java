package com.codecool.books.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoSql implements BookDao {
    private Connection conn;
    private AuthorDao authorDao;

    public BookDaoSql(Connection conn, AuthorDao authorDao) {
        this.conn = conn;
        this.authorDao = authorDao;
    }

    @Override
    public void save(Book book) {
        try {
            if (book.getId() == null) {
                String sql = "INSERT INTO book (author_id, title) VALUES (?, ?)";
                PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1, book.getAuthor().getId());
                st.setString(2, book.getTitle());
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
                book.setId(rs.getInt(1));
            } else {
                String sql = "UPDATE book SET author_id = ?, title = ? WHERE id = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, book.getAuthor().getId());
                st.setString(2, book.getTitle());
                st.setInt(3, book.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book get(int id) {
        try {
            String sql = "SELECT author_id, title FROM book WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            int authorId = rs.getInt(1);
            String title = rs.getString(2);

            Author author = authorDao.get(authorId);
            Book book = new Book(author, title);
            book.setId(id);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAll() {
        try {
            String sql = "SELECT id, author_id, title FROM book";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Book> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                int authorId = rs.getInt(2);
                String title = rs.getString(3);
                Author author = authorDao.get(authorId);
                Book book = new Book(author, title);

                book.setId(id);
                result.add(book);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book book) {
        try {
            String sql = "DELETE FROM book WHERE ID = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, book.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

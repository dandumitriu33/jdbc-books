package com.codecool.books.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoSql implements AuthorDao {
    Connection conn;

    public AuthorDaoSql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Author author) {
        try {
            if (author.getId() == -1) {
                String sql = "INSERT INTO author (first_name, last_name, birth_date) VALUES (?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                st.setString(1, author.getFirstName());
                st.setString(2, author.getLastName());
                st.setDate(3, author.getBirthDate());
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                rs.next();
                author.setId(rs.getInt(1));
            } else {
                String sql = "UPDATE author SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, author.getFirstName());
                st.setString(2, author.getLastName());
                st.setDate(3, author.getBirthDate());
                st.setInt(4, author.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author get(int id) {
        try {
            String sql = "SELECT first_name, last_name, birth_date FROM author WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Author author = new Author(rs.getString(1), rs.getString(2), rs.getDate(3));
            author.setId(id);
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> getAll() {
        try {
            String sql = "SELECT id, first_name, last_name, birth_date FROM author";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Author> result = new ArrayList<>();
            while (rs.next()) {
                Author author = new Author(rs.getString(2), rs.getString(3), rs.getDate(4));
                author.setId(rs.getInt(1));
                result.add(author);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Author author) {
        try {
            String sql = "DELETE FROM author WHERE ID = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, author.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

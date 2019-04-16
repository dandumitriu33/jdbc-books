package com.codecool.books.model;

import java.sql.*;
import java.util.List;

public class AuthorDaoJDBC implements AuthorDao {
    private Connection conn;

    public AuthorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Author author) {
        // TODO
    }

    @Override
    public void update(Author author) {
        // TODO
    }

    @Override
    public Author get(int id) {
        // TODO
        return null;
    }

    @Override
    public List<Author> getAll() {
        // TODO
        return null;
    }
}

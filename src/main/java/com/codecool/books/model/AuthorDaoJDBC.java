package com.codecool.books.model;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

public class AuthorDaoJDBC implements AuthorDao {
    private DataSource dataSource;

    public AuthorDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
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

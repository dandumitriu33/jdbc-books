package com.codecool.books.model;

import java.util.List;

public interface AuthorDao {
    void add(Author author);

    void update(Author author);

    Author get(int id);

    List<Author> getAll();

    void delete(Author author);
}

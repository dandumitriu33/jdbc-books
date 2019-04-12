package com.codecool.books.model;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void save(Author author);

    Author get(int id);

    List<Author> getAll();

    void delete(Author author);
}

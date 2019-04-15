package com.codecool.books.model;

import java.util.List;

public interface BookDao {
    void save(Book book);

    Book get(int id);

    List<Book> getAll();

    void delete(Book book);
}

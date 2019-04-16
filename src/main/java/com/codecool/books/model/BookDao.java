package com.codecool.books.model;

import java.util.List;

public interface BookDao {
    void add(Book book);

    void update(Book book);

    Book get(int id);

    List<Book> getAll();

    void delete(Book book);
}

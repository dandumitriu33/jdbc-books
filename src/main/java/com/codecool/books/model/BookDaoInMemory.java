package com.codecool.books.model;

import java.util.*;

public class BookDaoInMemory implements BookDao {
    Map<Integer, Book> books = new HashMap<>();
    int idCounter = 0;

    @Override
    public void save(Book book) {
        if (book.getId() == null) {
            book.setId(idCounter);
            idCounter++;
        }
        books.put(book.getId(), book);
    }

    @Override
    public Book get(int id) {
        return books.get(id);
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public void delete(Book book) {
        books.remove(book.getId());
    }
}

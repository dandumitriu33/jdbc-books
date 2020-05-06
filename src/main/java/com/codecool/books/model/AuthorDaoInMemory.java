package com.codecool.books.model;

import java.util.*;

public class AuthorDaoInMemory implements AuthorDao {
    Map<Integer, Author> authors = new HashMap<>();
    int idCounter = 0;

    @Override
    public void add(Author author) {
        author.setId(idCounter);
        idCounter++;
        authors.put(author.getId(), author);
    }

    @Override
    public void update(Author author) {
        authors.put(author.getId(), author);
    }

    @Override
    public Author get(int id) {
        return authors.get(id);
    }

    @Override
    public List<Author> getAll() {
        return new ArrayList<>(authors.values());
    }

    @Override
    public int getAuthorIdByLastName(String lastName) {
        for (Author authorIter :
                authors.values()) {
            if (authorIter.getLastName().equals(lastName)) return authorIter.getId();
        }
        return 0;
    }
}

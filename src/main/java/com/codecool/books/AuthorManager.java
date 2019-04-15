package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.AuthorDaoInMemory;
import com.codecool.books.model.AuthorDaoSql;
import com.codecool.books.view.UserInterface;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthorManager extends Manager {
    AuthorDao authorDao;

    public AuthorManager(UserInterface ui, AuthorDao authorDao) {
        super(ui);
        this.authorDao = authorDao;
    }

    @Override
    protected void add() {
        String firstName = ui.readString("First name", "X");
        String lastName = ui.readString("Last name", "Y");
        Date birthDate = ui.readDate("Birth date", Date.valueOf("1900-01-01"));
        authorDao.save(new Author(firstName, lastName, birthDate));
    }

    @Override
    protected String getName() {
        return "Author Manager";
    }

    @Override
    protected void list() {
        for (Author author: authorDao.getAll()) {
            ui.println(author);
        }
    }

    @Override
    protected void edit() {
        int id = ui.readInt("Author ID", 0);
        Author author = authorDao.get(id);
        if (author == null) {
            ui.println("Author not found!");
            return;
        }
        ui.println(author);

        String firstName = ui.readString("First name", author.getFirstName());
        String lastName = ui.readString("Last name", author.getLastName());
        Date birthDate = ui.readDate("Birth date", author.getBirthDate());
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        authorDao.save(author);
    }

    @Override
    protected void delete() {
        int id = ui.readInt("Author ID", 0);
        Author author = authorDao.get(id);
        if (author == null) {
            ui.println("Author not found!");
            return;
        }
        authorDao.delete(author);
    }
}

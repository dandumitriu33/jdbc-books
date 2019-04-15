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

public class AuthorManager {

    UserInterface ui;
    AuthorDao authorDao;

    public AuthorManager(UserInterface ui, AuthorDao authorDao) {
        this.ui = ui;
        this.authorDao = authorDao;
    }

    public void run() {
        boolean running = true;

        while (running) {
            ui.printTitle("Author Manager");
            ui.printOption('l', "List authors");
            ui.printOption('a', "Add author");
            ui.printOption('e', "Edit author");
            ui.printOption('d', "Delete author");
            ui.printOption('q', "Quit");

            switch (ui.choice("laedq")) {
                case 'l':
                    listAuthors();
                    break;
                case 'a':
                    addAuthor();
                    break;
                case 'e':
                    editAuthor();
                    break;
                case 'd':
                    deleteAuthor();
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    private void addAuthor() {
        String firstName = ui.readString("First name", "X");
        String lastName = ui.readString("Last name", "Y");
        Date birthDate = ui.readDate("Birth date", Date.valueOf("1900-01-01"));
        authorDao.save(new Author(firstName, lastName, birthDate));
    }

    private void listAuthors() {
        for (Author author: authorDao.getAll()) {
            System.out.println(author);
        }
    }

    private void editAuthor() {
        int id = ui.readInt("Author ID", 0);
        Author author = authorDao.get(id);
        if (author == null) {
            System.out.println("Author not found!");
            return;
        }
        System.out.println(author);

        String firstName = ui.readString("First name", author.getFirstName());
        String lastName = ui.readString("Last name", author.getLastName());
        Date birthDate = ui.readDate("Birth date", author.getBirthDate());
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthDate(birthDate);
        authorDao.save(author);
    }

    private void deleteAuthor() {
        int id = ui.readInt("Author ID", 0);
        Author author = authorDao.get(id);
        if (author == null) {
            System.out.println("Author not found!");
            return;
        }
        authorDao.delete(author);
    }
}

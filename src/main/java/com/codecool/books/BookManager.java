package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.AuthorDaoInMemory;
import com.codecool.books.view.UserInterface;

import java.sql.Date;

public class BookManager {

    UserInterface ui;
    AuthorDao authorDao;

    public BookManager() {
        ui = new UserInterface(System.in, System.out);
    }

    public void run() {
        authorDao = new AuthorDaoInMemory();

        authorDao.save(new Author("J.R.R.", "Tolkien", Date.valueOf("1982-01-03")));
        authorDao.save(new Author("Douglas", "Adams", Date.valueOf("1952-03-11")));
        authorDao.save(new Author("George R. R.", "Martin", Date.valueOf("1948-09-20")));
        authorDao.save(new Author("Frank", "Herbert", Date.valueOf("1920-10-08")));

        boolean running = true;

        while (running) {
            ui.printTitle("Main Menu");
            ui.printOption('l', "list authors");
            ui.printOption('a', "add author");
            ui.printOption('e', "edit author");
            ui.printOption('d', "delete author");
            ui.printOption('q', "quit");

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

    public static void main(String[] args) {
        new BookManager().run();
    }
}

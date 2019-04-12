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
            ui.printOption('d', "delete author");
            ui.printOption('q', "quit");

            switch (ui.choice("ladq")) {
                case 'l':
                    for (Author author: authorDao.getAll()) {
                        System.out.println(author);
                    }
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new BookManager().run();
    }
}

package com.codecool.books;

import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.AuthorDaoInMemory;
import com.codecool.books.view.UserInterface;

public class BookManager {

    UserInterface ui;
    AuthorDao authorDao;

    public BookManager() {
        ui = new UserInterface(System.in, System.out);
    }

    public void run() {
        authorDao = new AuthorDaoInMemory();
        boolean running = true;

        while (running) {
            ui.printOption('l', "list authors");
            ui.printOption('a', "add author");
            ui.printOption('d', "delete author");
            ui.printOption('q', "quit");

            switch (ui.choice("ladq")) {
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

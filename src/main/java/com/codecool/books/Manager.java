package com.codecool.books;

import com.codecool.books.view.UserInterface;

import java.sql.SQLException;

public abstract class Manager {
    protected UserInterface ui;

    public Manager(UserInterface ui) {
        this.ui = ui;
    }

    public void run() throws SQLException {
        boolean running = true;

        while (running) {
            ui.printTitle(getName());
            ui.printOption('l', "List");
            ui.printOption('a', "Add");
            ui.printOption('e', "Edit");
            ui.printOption('q', "Quit");

            switch (ui.choice("laeq")) {
                case 'l':
                    list();
                    break;
                case 'a':
                    add();
                    break;
                case 'e':
                    edit();
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    protected abstract String getName();
    protected abstract void list() throws SQLException;
    protected abstract void add();
    protected abstract void edit();
}

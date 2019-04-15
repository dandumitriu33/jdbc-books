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

public class Main {
    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface(System.in, System.out);
        new Main(ui).run();
    }

    UserInterface ui;
    AuthorDao authorDao;

    Main(UserInterface ui) {
        this.ui = ui;
    }

    private void run() throws SQLException {
        setup();

        boolean running = true;

        while (running) {
            ui.printTitle("Main Menu");
            ui.printOption('a', "Authors");
            ui.printOption('b', "Books");
            ui.printOption('q', "Quit");
            switch (ui.choice("abq")) {
                case 'a':
                    new AuthorManager(ui, authorDao).run();
                    break;
                case 'b':
                    break;
                case 'q':
                    running = false;
                    break;
            }
        }
    }

    private void setup() throws SQLException {
        ui.printOption('i', "In-memory database");
        ui.printOption('s', "SQL database");
        switch (ui.choice("is")) {
            case 'i':
                authorDao = new AuthorDaoInMemory();
                System.out.println("Using in-memory database");
                createInitialData();
                break;
            case 's':
                // TODO: connection parameters
                System.out.println("Connecting to SQL database");
                String url = "jdbc:postgresql:books";
                Connection conn = DriverManager.getConnection(url, "pawel", "pawel");
                authorDao = new AuthorDaoSql(conn);
                break;
        }
    }


    private void createInitialData() {
        System.out.println("Creating initial data");
        authorDao.save(new Author("J.R.R.", "Tolkien", Date.valueOf("1982-01-03")));
        authorDao.save(new Author("Douglas", "Adams", Date.valueOf("1952-03-11")));
        authorDao.save(new Author("George R. R.", "Martin", Date.valueOf("1948-09-20")));
        authorDao.save(new Author("Frank", "Herbert", Date.valueOf("1920-10-08")));
    }

}

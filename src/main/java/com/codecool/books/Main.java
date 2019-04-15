package com.codecool.books;

import com.codecool.books.model.*;
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
    BookDao bookDao;

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
                    new BookManager(ui, bookDao, authorDao).run();
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
                System.out.println("Using in-memory database");
                authorDao = new AuthorDaoInMemory();
                bookDao = new BookDaoInMemory();
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

        Author author1 = new Author("J.R.R.", "Tolkien", Date.valueOf("1982-01-03"));
        Author author2 = new Author("Douglas", "Adams", Date.valueOf("1952-03-11"));
        Author author3 = new Author("George R. R.", "Martin", Date.valueOf("1948-09-20"));
        Author author4 = new Author("Frank", "Herbert", Date.valueOf("1920-10-08"));

        authorDao.save(author1);
        authorDao.save(author2);
        authorDao.save(author3);
        authorDao.save(author4);

        bookDao.save(new Book(author1, "Hobbit"));
        bookDao.save(new Book(author1, "Lord of the Rings"));
        bookDao.save(new Book(author2, "Hitchhiker's Guide to the Galaxy"));
        bookDao.save(new Book(author3, "A Game of Thrones"));
        bookDao.save(new Book(author3, "Tuf Voyaging"));
        bookDao.save(new Book(author4, "Dune"));
    }

}

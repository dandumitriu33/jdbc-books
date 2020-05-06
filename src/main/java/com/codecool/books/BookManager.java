package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.Book;
import com.codecool.books.model.BookDao;
import com.codecool.books.view.UserInterface;

import java.sql.Date;
import java.sql.SQLException;

public class BookManager extends Manager {
    BookDao bookDao;
    AuthorDao authorDao;

    public BookManager(UserInterface ui, BookDao bookDao, AuthorDao authorDao) {
        super(ui);
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    protected String getName() {
        return "Book Manager";
    }

    @Override
    protected void list() throws SQLException {
        for (Book book: bookDao.getAll()) {
            ui.println("Book " + book.getId() + ": " + book.getTitle() + " by " + book.getAuthor().getFirstName() + " "
                    + book.getAuthor().getLastName() + " author ID: " + book.getAuthor().getId());
        }
    }

    @Override
    protected void add() {
        String title = ui.readString("Title ", "X");
        String firstName = ui.readString("Author first name", "Y");
        String lastName = ui.readString("Author last name", "Z");
        Date birthDate = ui.readDate("Author birth date", Date.valueOf("1900-01-01"));
        Author author = new Author(firstName, lastName, birthDate);
        authorDao.add(author);
        bookDao.add(new Book(author, title));
    }

    @Override
    protected void edit() {
        int id = ui.readInt("Book ID", 0);
        Book book = bookDao.get(id);
        if (book == null) {
            ui.println("Book not found!");
            return;
        }
        ui.println("Book " + book.getId() + ": " + book.getTitle() + " by " + book.getAuthor().getFirstName() + " "
                + book.getAuthor().getLastName() + " author ID: " + book.getAuthor().getId());
        String title = ui.readString("New book title", book.getTitle());
        int authorId = ui.readInt("New author ID", book.getAuthor().getId());
        Author author = authorDao.get(authorId);
        book.setTitle(title);
        book.setAuthor(author);
        bookDao.update(book);
    }
}

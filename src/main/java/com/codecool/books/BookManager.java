package com.codecool.books;

import com.codecool.books.model.Author;
import com.codecool.books.model.AuthorDao;
import com.codecool.books.model.Book;
import com.codecool.books.model.BookDao;
import com.codecool.books.view.UserInterface;

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
    protected void list() {
        for (Book book: bookDao.getAll()) {
            System.out.println(book);
        }
    }

    @Override
    protected void add() {
        System.out.println("Authors:");
        for (Author author: authorDao.getAll()) {
            System.out.println(author);
        }
        int authorId = ui.readInt("Author ID", 0);
        String title = ui.readString("Title", "Z");

        Author author = authorDao.get(authorId);
        if (author == null) {
            System.out.println("Author not found!");
            return;
        }

        bookDao.save(new Book(author, title));
    }

    @Override
    protected void edit() {
        int id = ui.readInt("Book ID", 0);
        Book book = bookDao.get(id);
        if (book == null) {
            System.out.println("Author not found!");
            return;
        }
        System.out.println(book);

        System.out.println("Authors:");
        for (Author author: authorDao.getAll()) {
            System.out.println(author);
        }

        int authorId = ui.readInt("Author ID", book.getAuthor().getId());
        String title = ui.readString("Title", book.getTitle());

        Author author = authorDao.get(authorId);
        if (author == null) {
            System.out.println("Author not found!");
            return;
        }

        book.setAuthor(author);
        book.setTitle(title);
        bookDao.save(book);
    }

    @Override
    protected void delete() {
        int id = ui.readInt("Book ID", 0);
        Book book = bookDao.get(id);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        bookDao.delete(book);
    }
}

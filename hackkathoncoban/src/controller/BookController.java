package controller;

import modal.Book;
import service.BookService;

public class BookController {
    public static BookService bookService = new BookService();

    public static Book[] getAll() {
        return bookService.getAll();
    }

    public static int getSize() {
        return bookService.getSize();
    }

    public static void save(Book book) {
        bookService.save(book);
    }

    public static boolean delete(int id) {
        bookService.deleteBook(id);
        return false;
    }

    public static Book findById(int id) {
        return bookService.findById(id);
    }

    public static int getNewId() {
        return bookService.getNewId();
    }

}

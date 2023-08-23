package dao;

import model.Book;

import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    Book getBookById(int id);
    List<Book> getAllBooks();
    List<Book> getBooksByAuthorId(int authorId);
    void updateBook(Book book);
    void deleteBook(int id);
}

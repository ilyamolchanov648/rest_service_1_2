package dao;

import model.Author;

import java.util.List;

public interface AuthorDAO {
    void addAuthor(Author author);
    Author getAuthorById(int id);
    List<Author> getAllAuthors();
    void updateAuthor(Author author);
    void deleteAuthor(int id);
    // Дополнительные методы для работы с данными, например, получение книг автора и т.д.
}

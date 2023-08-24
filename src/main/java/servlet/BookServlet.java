package servlet;

import dao.BookDAO;
import dao.BookDAOImpl;
import model.Book;
import utils.DBConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;




public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = DBConnectionUtil.getConnection();
        bookDAO = new BookDAOImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Реализация метода doGet для получения списка книг
        List<Book> books = bookDAO.getAllBooks();

        // Отправить список книг в формате JSON или на страницу
        ObjectMapper objectMapper = new ObjectMapper();
        String booksJson = objectMapper.writeValueAsString(books);

        response.setContentType("application/json");
        response.getWriter().write(booksJson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthorId(authorId);

        bookDAO.addBook(newBook);

        response.setStatus(HttpServletResponse.SC_CREATED); // Created
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setTitle(title);
        updatedBook.setAuthorId(authorId);

        bookDAO.updateBook(updatedBook);

        response.setStatus(HttpServletResponse.SC_OK); // OK
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        bookDAO.deleteBook(id);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT); // No Content
    }
}

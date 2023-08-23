
package servlet;

import dao.AuthorDAO;
import dao.AuthorDAOImpl;
import model.Author;
import utils.DBConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class AuthorServlet extends HttpServlet {
    private AuthorDAO authorDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = DBConnectionUtil.getConnection();
        authorDAO = new AuthorDAOImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorDAO.getAllAuthors();
        // Отправить список авторов в формате JSON
        // Пример отправки JSON:
        // response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write(new Gson().toJson(authors));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Author author = new Author(0, name);
        authorDAO.addAuthor(author);
        // Отправить успешный ответ или ошибку в формате JSON
        // Пример отправки успешного JSON-ответа:
        // response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write("{\"message\": \"Автор успешно добавлен\"}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Author author = new Author(authorId, name);
        authorDAO.updateAuthor(author);
        // Отправить успешный ответ или ошибку в формате JSON
        // Пример отправки успешного JSON-ответа:
        // response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write("{\"message\": \"Данные автора успешно обновлены\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("id"));
        authorDAO.deleteAuthor(authorId);
        // Отправить успешный ответ или ошибку в формате JSON
        // Пример отправки успешного JSON-ответа:
        // response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write("{\"message\": \"Автор успешно удален\"}");
    }
}


package servlet;

import dao.CourseDAO;
import dao.CourseDAOImpl;
import model.Course;
import utils.DBConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = DBConnectionUtil.getConnection();
        courseDAO = new CourseDAOImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();

        ObjectMapper objectMapper = new ObjectMapper();
        String coursesJson = objectMapper.writeValueAsString(courses);

        response.setContentType("application/json");
        response.getWriter().write(coursesJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        Course newCourse = new Course();
        newCourse.setTitle(title);
        newCourse.setAuthorId(authorId);

        courseDAO.addCourse(newCourse);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        Course updatedCourse = new Course();
        updatedCourse.setId(id);
        updatedCourse.setTitle(title);
        updatedCourse.setAuthorId(authorId);

        courseDAO.updateCourse(updatedCourse);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        courseDAO.deleteCourse(id);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}

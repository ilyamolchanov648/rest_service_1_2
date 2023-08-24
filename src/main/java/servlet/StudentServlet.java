package servlet;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;
import utils.DBConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = DBConnectionUtil.getConnection();
        studentDAO = new StudentDAOImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();

        ObjectMapper objectMapper = new ObjectMapper();
        String studentsJson = objectMapper.writeValueAsString(students);

        response.setContentType("application/json");
        response.getWriter().write(studentsJson);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Student newStudent = new Student();
        newStudent.setName(name);

        studentDAO.addStudent(newStudent);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Student updatedStudent = new Student();
        updatedStudent.setId(id);
        updatedStudent.setName(name);

        studentDAO.updateStudent(updatedStudent);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        studentDAO.deleteStudent(id);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
